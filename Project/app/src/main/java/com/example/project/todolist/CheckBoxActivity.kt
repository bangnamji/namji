package com.example.project.todolist

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.databinding.ActivityCheckBoxBinding
import com.example.project.databinding.TodolistCheckboxBinding

class CheckBoxActivity : AppCompatActivity() {
    private val data = ArrayList<TodoCheckList>()

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var binding: ActivityCheckBoxBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckBoxBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //데이터 저장
//        val pref = PreferenceManager.getDefaultSharedPreferences(this)
//        pref.apply {
//            val mainlist = getString("MAIN_LIST", "")
//            binding.editText.setText(mainlist)
//        }


        binding.recyclerView.apply{
            layoutManager = LinearLayoutManager(this@CheckBoxActivity)

            adapter = TodoCheckAdapter(data,
                    onClickDeleteIcon = {
                        deleteTodo(it)
                    },
                    onClickItem = {
                        toggleTodo(it)
                    }

            )
        }

        binding.addButton.setOnClickListener {
            addTodo()

        }


    }




    //할 일 완료 기능 -> 할 일을 선택하면 !todo.isDone으로 변경된다.
    private fun toggleTodo(todo: TodoCheckList) {
        todo.isDone = !todo.isDone
        binding.recyclerView.adapter?.notifyDataSetChanged()

    }

    //할 일 추가 기능 -> 변경된 데이터를 어댑터에 전달한다.
    private fun addTodo() {
        val todo = TodoCheckList(binding.editText.text.toString())
        data.add(todo)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    //삭제 기능
    private fun deleteTodo(todo: TodoCheckList) {
        data.remove(todo)
        binding.recyclerView.adapter?.notifyDataSetChanged()

    }

}


data class TodoCheckList(
    val text: String,
    var isDone: Boolean=false
)

//Unit은 리턴값이 없는 함수
class TodoCheckAdapter(
        private val myDataset: ArrayList<TodoCheckList>,
        val onClickDeleteIcon: (todo: TodoCheckList) -> Unit,
        val onClickItem: (todo: TodoCheckList) -> Unit) :
    RecyclerView.Adapter<TodoCheckAdapter.TodoCheckViewHolder>() {

    inner class TodoCheckViewHolder(val binding: TodolistCheckboxBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoCheckViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todolist_checkbox, parent, false)
        return TodoCheckViewHolder(TodolistCheckboxBinding.bind(view))

    }

    override fun onBindViewHolder(holder: TodoCheckViewHolder, position: Int) {
        val todo = myDataset[position]
        holder.binding.todoText.text = todo.text

        //만약 할 일을 완료했다면
        if (todo.isDone) {
            holder.binding.todoText.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                setTypeface(null, Typeface.ITALIC)
            }
        } else {
            holder.binding.todoText.apply {
                paintFlags = 0
                setTypeface(null, Typeface.NORMAL)
            }

        }

        holder.binding.deleteImageView.setOnClickListener {
            onClickDeleteIcon.invoke(todo)
        }
        holder.binding.checkbox.setOnClickListener {
            onClickItem.invoke(todo)
        }



        holder.binding.editImageView.setOnClickListener {
            val intent = Intent(holder.itemView?.context, Modify::class.java)
//            intent.putExtra(holder.itemView?.context,todo.text)
//            intent.putExtra("hi", myDataset[position].text)
            holder.itemView?.context.startActivity(intent)      //상세페이지로 intent...
        }


    }


    override fun getItemCount(): Int {
        return myDataset.size
    }

}