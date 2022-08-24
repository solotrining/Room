package com.example.room2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room2.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    var listData = mutableListOf<RoomMemo>()
    var helper: RoomHelper? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData[position]
        holder.setRoomMemo(memo)
    }

    override fun getItemCount(): Int {
        return listData.size
    }


    inner class Holder(private val binding : ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
        var mRoomMemo : RoomMemo? = null

        init {
            binding.deletebtn.setOnClickListener {
                helper?.roomMemoDao()?.delete(mRoomMemo!!)
                listData.remove(mRoomMemo)
                notifyDataSetChanged()
            }

            binding.editbtn.setOnClickListener {
                val m = RoomMemo("수정됨", 34000000)

                m.num = mRoomMemo?.num
                helper?.roomMemoDao()?.insert(m)
                listData.remove(mRoomMemo)
                listData.add(m)
                notifyDataSetChanged()
            }
        }


        fun setRoomMemo(memo: RoomMemo) {
            mRoomMemo = memo
            binding.textNum.text = "${memo.num}"
            binding.textContent.text = memo.content
            val sdf = SimpleDateFormat("YYYY/MM/dd hh:mm")
            binding.textDatetime.text = sdf.format(memo.datetime)
        }
    }
}
