package com.example.somoni.presenetation.uimain.partAdvance.partusually.usuallyadapter

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.somoni.R
import com.example.somoni.data.model.Regular
import com.example.somoni.extensions.SomoniApp
import com.example.somoni.extensions.utils.CURRENCY_TYPE
import com.example.somoni.extensions.utils.Currency
import kotlinx.android.synthetic.main.list_usual.view.*
import java.io.File
import java.io.FileOutputStream

class UsuallyAdapter (private val onItemClick:(Regular, Int) -> Unit,) : RecyclerView.Adapter<UsuallyAdapter.CurrencyUsualHolder>() {
    private var getList = emptyList<Regular>()
    fun get(listCurrency: List<Regular>){
        getList = listCurrency
        notifyDataSetChanged()
    }
    class CurrencyUsualHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyUsualHolder {
        return CurrencyUsualHolder(
            LayoutInflater.from(parent.context).
            inflate(R.layout.list_usual,parent,false))
    }

    override fun onBindViewHolder(holder: CurrencyUsualHolder, position: Int) {
        val currentItem = getList[position]

        fun findValueIdByName(name: String): Int {
            var id = 0
            for (i in 0 until currentItem.Currency.size) {
                if (currentItem.Currency[i].name == name) {
                    id = i;
                    break
                }
            }
            return id
        }
        val indexInCurrencyList: Int = when (SomoniApp.sharedPreferences.getInt(CURRENCY_TYPE, -1)) {
           Currency.RUB.currencyId -> {
                findValueIdByName(holder.itemView.context.getString(R.string.lg_russian))
            }
            Currency.EUR.currencyId -> {
                findValueIdByName(holder.itemView.context.getString(R.string.lg_euro))
            }
            Currency.USD.currencyId -> {
                findValueIdByName(holder.itemView.context.getString(R.string.lg_usa))
            }
            else -> {
                findValueIdByName(holder.itemView.context.getString(R.string.name_tjk_lg))
            }
        }
        holder.itemView.name_bank.text = getList[position].bank_name
        holder.itemView.icon_bank.load(getList[position].icon) {
            crossfade(true)
            placeholder(R.drawable.ic_refresh)
        }
        holder.itemView.icon_bank
        holder.itemView.name_currency.text = getList[position].Currency[indexInCurrencyList].name
        holder.itemView.buy_currency.text = getList[position].Currency[indexInCurrencyList].buy_value
        holder.itemView.sale_currency.text = getList[position].Currency[indexInCurrencyList].sell_value
        val content =  holder.itemView.content
        holder.itemView.list_tranfer_usualy.setOnClickListener {
            val popupMenu = PopupMenu(holder.itemView.context,it)
            popupMenu.inflate(R.menu.menu_adapter_item)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.convertationCurrency ->{
                        onItemClick.invoke(currentItem,0)
                        return@OnMenuItemClickListener true
                    }
                    R.id.shareCurrency -> {
                        val bitmap = Bitmap.createBitmap(content.width,content.height,
                            Bitmap.Config.ARGB_8888)
                        var canvas = Canvas(bitmap)
                        content.draw(canvas)
                        val file = File(holder.itemView.context.applicationContext.getExternalFilesDir(null),"image.jpg")
                        val fileOutputStream = FileOutputStream(file)
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream)
                        fileOutputStream.flush()
                        fileOutputStream.close()
                        val uri = FileProvider.getUriForFile(holder.itemView.context.applicationContext,"com.example.somoni",file)
                        var intent = Intent()
                        intent.action = Intent.ACTION_SEND
                        intent.putExtra(Intent.EXTRA_TEXT,"share")
                        intent.putExtra(Intent.EXTRA_STREAM,uri)
                        intent.type = "text/plain"
                        holder.itemView.context.startActivity(intent)
                        return@OnMenuItemClickListener true
                    }
                    else -> false
                }
            })
            popupMenu.show()
        }
        holder.itemView.setOnClickListener(View.OnClickListener {
            onItemClick.invoke(currentItem,0)
        })
    }
    override fun getItemCount() : Int {
        return getList.size
    }
}