package com.example.vendasmae.view.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.lifecycle.Observer
import com.example.vendasmae.R
import com.example.vendasmae.baseClass.BaseFragment
import com.example.vendasmae.databinding.FragmentMainBinding
import com.example.vendasmae.entities.MainDataBase
import com.example.vendasmae.repository.*
import com.example.vendasmae.util.RetrofitUtil
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_main.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainFrag : BaseFragment() {
    private val TAG = "mainFrag"

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var mBinding: FragmentMainBinding// by lazy { FragmentMainBinding.inflate(layoutInflater)}

    val retrofit = RetrofitUtil.getRetrofit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }




    }

    val vendedorasRepo by lazy{
        val vendedoraDao = MainDataBase.getInstance(activity!!.applicationContext).vendedoraDao()
        VendedorasRepository(vendedoraDao, retrofit)
    }
    val itemRepo by lazy{
        val itemDao = MainDataBase.getInstance(activity!!.applicationContext).produtoDao()
        ProdutoRepository(itemDao, retrofit)
    }
    val vendaRepo by lazy{
        val vendaDao = MainDataBase.getInstance(activity!!.applicationContext).vendaDao()
        VendaRepository(vendaDao, retrofit)
    }

    val tipoRepo by lazy{
        val tipoDao = MainDataBase.getInstance(activity!!.applicationContext).tipoDao()
        TipoRepository(tipoDao, retrofit)
    }

    val maletaRepo by lazy{
        val maletaDao = MainDataBase.getInstance(activity!!.applicationContext).maletaDao()
        MaletaRepository(maletaDao, retrofit)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMainBinding.inflate(inflater, container, false)

        vendedorasRepo.getVendedoraValorQuantidade().observe(this, Observer {
            it?.let {

                val sets: ArrayList<IBarDataSet> = ArrayList()

                for(i in it.indices) {
                    val entries = mutableListOf<BarEntry>()
                    val entry = BarEntry(i.toFloat(), it[i].valorVendido)
                    entries.add(0, entry)
//                    entries.add(1, BarEntry(i.toFloat(), 400f))

                    val dataSet = BarDataSet(entries, it[i].vendedora.nome)
                    dataSet.setColor(ColorTemplate.COLORFUL_COLORS[i])
                   // dataSet.setColor(100*i)
                   // dataSet.valueTextColor = i*100
                    sets.add(dataSet)
                }

                class formatter: ValueFormatter(){
                    override fun getFormattedValue(value: Float): String {
                        return "R$ $value"
                    }
                }

                val format = formatter()

                val lineData = BarData(sets)
                lineData.setValueTypeface(Typeface.SERIF)
                lineData.setValueTextSize(15f)
                lineData.setValueFormatter(format)



                Log.d(TAG, lineData.colors.contentToString())
                val chart = activity!!.findViewById<BarChart>(R.id.chart)//mBinding.chart
                chart.setData(lineData)
                chart.setFitBars(true)

                chart.invalidate()

            }
        })

        return mBinding.root
    }

    override fun adiciona() {
        Log.d("add", "notImplemented")
    }


}