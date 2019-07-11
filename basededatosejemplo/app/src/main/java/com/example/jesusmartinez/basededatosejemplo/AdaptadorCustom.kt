package com.example.jesusmartinez.basededatosejemplo

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import com.example.jesusmartinez.ejemplorecyclerview.ClickListener
import com.example.jesusmartinez.ejemplorecyclerview.LongClickListener


class AdaptadorCustom(items:ArrayList<Alumno>, var listener: ClickListener, var longClickListener: LongClickListener):RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {

    var items:ArrayList<Alumno>?=null
    //Guardar indices de los elementos seleccionados
    var itemsSeleccionados:ArrayList<Int>?=null
    var viewholder:ViewHolder?=null
    var multiSeleccion= false
    init {
        this.items=items
        itemsSeleccionados=ArrayList()
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AdaptadorCustom.ViewHolder {
        val vista= LayoutInflater.from(p0.context).inflate(R.layout.template_alumno,p0,false)

        viewholder=ViewHolder(vista,listener,longClickListener)
        return  viewholder!!
    }

    override fun getItemCount(): Int {
        return  items?.count()!!
    }

    override fun onBindViewHolder(holder: AdaptadorCustom.ViewHolder, p1: Int) {
        val item=items?.get(p1)
        holder.nombre?.text=item?.Nombre!!
        holder.id?.text=item?.Id!!

        if(itemsSeleccionados?.contains(p1)!!)
        {
            holder.vista.setBackgroundColor(Color.LTGRAY)
        }else{
            holder.vista.setBackgroundColor(Color.WHITE)
        }
    }

    fun iniciarActionMode()
    {
        multiSeleccion=true
    }
    fun destruirActionMode()
    {
        multiSeleccion=false
        itemsSeleccionados?.clear()
        notifyDataSetChanged()
    }
    fun terminarActionMode()
    {
        for (item in itemsSeleccionados!!)
        {
            itemsSeleccionados?.remove(item)
        }
        multiSeleccion=false
        notifyDataSetChanged()
    }
    fun seleccionarItem(index:Int)
    {
        if(multiSeleccion)
        {
            if(itemsSeleccionados?.contains(index)!!){
                itemsSeleccionados?.removeAt(index)
            }
            else{
                itemsSeleccionados?.add((index))
            }

        }
        notifyDataSetChanged()
    }
    fun obtenerElementosSeleccionados():Int
    {
        return  itemsSeleccionados?.count()!!
    }
    fun eliminarSeleccionados(){
        if(itemsSeleccionados?.count()!!>0){
            var itemsEliminados=ArrayList<Alumno>()

            for (index in itemsSeleccionados!!)
            {
                itemsEliminados.add(items?.get(index)!!)

            }
            items?.removeAll(itemsEliminados)
            itemsSeleccionados?.clear()
        }
    }

    class ViewHolder(vista:View,listener: ClickListener,longClickListener: LongClickListener):RecyclerView.ViewHolder(vista),
        OnClickListener,View.OnLongClickListener{


        var vista=vista

        var nombre:TextView?=null
        var id:TextView?=null

        var listener:ClickListener?=null
        var longlistener:LongClickListener?=null

        init {
            this.nombre=vista.findViewById(R.id.tvNombre)
            this.id=vista.findViewById(R.id.tvId)

            this.listener=listener
            this.longlistener=longClickListener
            vista.setOnClickListener(this)
            vista.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!,adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            this.longlistener?.longClick(v!!,adapterPosition)
            return true
        }

    }

}