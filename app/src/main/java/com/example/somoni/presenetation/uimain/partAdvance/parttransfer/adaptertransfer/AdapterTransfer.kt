package com.example.somoni.presenetation.uimain.partAdvance.parttransfer.adaptertransfer

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.somoni.R
import com.example.somoni.data.model.Regular
import java.io.File
import java.io.FileOutputStream


class AdapterTransfer(val onItemClick: (Regular, Int) -> Unit,) :
    RecyclerView.Adapter<AdapterTransfer.CurrencyTransferHolder>() {
    private var getList = emptyList<Regular>()
    fun get(listCurrency: List<Regular>) {
        getList = listCurrency
        notifyDataSetChanged()
    }
    class CurrencyTransferHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name_bank = itemView.findViewById<TextView>(R.id.name_bank)
        val icon_bank = itemView.findViewById<ImageView>(R.id.icon_bank)
        val name_currency = itemView.findViewById<TextView>(R.id.name_currency)
        val buy_currency = itemView.findViewById<TextView>(R.id.buy_currency)
        val sale_currency = itemView.findViewById<TextView>(R.id.sale_currency)
        val list_transfer = itemView.findViewById<ImageView>(R.id.list_tranfer_rus)
        val content = itemView.findViewById<CardView>(R.id.content)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyTransferHolder {
        return CurrencyTransferHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_list_transfer, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CurrencyTransferHolder, position: Int) {
        val currentItem = getList[position]
        holder.name_bank.text = getList[position].bank_name
        holder.icon_bank.load(getList[position].icon) {
            crossfade(true)
            placeholder(R.drawable.ic_refresh)
        }
        holder.name_currency.text = getList[position].Currency[0].name
        holder.buy_currency.text =
            getList[position].Currency[0].buy_value
        holder.sale_currency.text =
            getList[position].Currency[0].sell_value
        holder.itemView.setOnClickListener {
            onItemClick.invoke(currentItem,0)
        }
        holder.list_transfer.setOnClickListener {
            val popupMenu = PopupMenu(holder.itemView.context,it)
            popupMenu.inflate(R.menu.menu_adapter_item)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                if(item.itemId == R.id.convertationCurrency){
                    onItemClick.invoke(currentItem,0)
                    return@OnMenuItemClickListener true
                }else if(item.itemId ==R.id.shareCurrency){
                    val bitmap = Bitmap.createBitmap(holder.content.width,holder.content.height,
                        Bitmap.Config.ARGB_8888)
                    var canvas = Canvas(bitmap)
                    holder.content.draw(canvas)
                    val file = File(holder.itemView.context.applicationContext.getExternalFilesDir(null),"image.jpg")
                    val fileOutputStream = FileOutputStream(file)
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream)
                    fileOutputStream.flush()
                    fileOutputStream.close()
                    val uri = FileProvider.getUriForFile(holder.itemView.context.applicationContext,"com.example.example",file)
                    var intent = Intent()
                    intent.action = Intent.ACTION_SEND
                    intent.putExtra(Intent.EXTRA_TEXT,"share")
                    intent.putExtra(Intent.EXTRA_STREAM,uri)
                    intent.type = "text/plain"
                    holder.itemView.context.startActivity(intent)
                    return@OnMenuItemClickListener true
                }
                return@OnMenuItemClickListener true
            })
            popupMenu.show()
        }
    }



    override fun getItemCount(): Int {
        return getList.size
    }
}