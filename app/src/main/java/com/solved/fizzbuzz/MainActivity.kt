package com.solved.fizzbuzz

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<AppCompatButton>(R.id.button_go).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val int1View = findViewById<AppCompatEditText>(R.id.int1)
        val int2View = findViewById<AppCompatEditText>(R.id.int2)
        val limitView = findViewById<AppCompatEditText>(R.id.limit)
        val str1View = findViewById<AppCompatEditText>(R.id.str1)
        val str2View = findViewById<AppCompatEditText>(R.id.str2)

        val int1Str = int1View.text.toString()
        val int2Str = int2View.text.toString()
        val limitStr = limitView.text.toString()
        val str1 = str1View.text.toString()
        val str2 = str2View.text.toString()

        with(findViewById<AppCompatTextView>(R.id.result)){
            //check input arguments
            FizzBuzzUtil.checkArguments(int1Str, int2Str, limitStr, str1, str2).let {
                if (it != null) { //it means there is an argument error
                    val errorCodes: Array<String> = resources.getStringArray(R.array.error_msgs)
                    setTextColor(getColor(android.R.color.holo_red_dark))
                    if (it < errorCodes.size) {
                        text = errorCodes[it]
                    } else {
                        text = getString(R.string.default_error_msg)
                    }
                    return
                }
                //if it == null, it means the arguments are valid, so we compute the result below
            }
            //compute the result
            val result = FizzBuzzUtil.FizzBuzz(
                int1Str.toInt(), int2Str.toInt(), limitStr.toInt(), str1.trim(), str2.trim()
            )
            //display the result
            setTextColor(getColor(android.R.color.black))
            movementMethod = ScrollingMovementMethod()
            text = result

            //update statistics
            FizzBuzzUtil.putInt1(int1Str.toInt())
            FizzBuzzUtil.putInt2(int2Str.toInt())
            FizzBuzzUtil.putLimit(limitStr.toInt())
            //display statistics
            populateChart()
        }

    }


    fun populateChart() {

        with (findViewById<LineChart>(R.id.chart)) {
            val valsInt1 = ArrayList<Entry>()
            val valsInt2 = ArrayList<Entry>()
            val valsLimit = ArrayList<Entry>()

            //fill the entry lists with data for int1, int2 and limit
            for (key in 1..FizzBuzzUtil.MAX_LIMIT) {
                var value = FizzBuzzUtil.getInt1(key)
                if (value != 0) {
                    valsInt1.add(Entry(key.toFloat(), value.toFloat()))
                }
                value = FizzBuzzUtil.getInt2(key)
                if (value != 0) {
                    valsInt2.add(Entry(key.toFloat(), value.toFloat()))
                }
                value = FizzBuzzUtil.getLimit(key)
                if (value != 0) {
                    valsLimit.add(Entry(key.toFloat(), value.toFloat()))
                }
            }

            //create datasets for each entry list
            val setInt1 = LineDataSet(valsInt1, getString(R.string.label_int1))
            setInt1.axisDependency = YAxis.AxisDependency.LEFT
            setInt1.color = getColor(android.R.color.holo_blue_light)
            val setInt2 = LineDataSet(valsInt2, getString(R.string.label_int2))
            setInt2.axisDependency = YAxis.AxisDependency.LEFT
            setInt2.color = getColor(android.R.color.holo_orange_light)
            val setLimit = LineDataSet(valsLimit, getString(R.string.label_limit))
            setLimit.axisDependency = YAxis.AxisDependency.LEFT
            setLimit.color = getColor(android.R.color.holo_red_light)

            val sets = ArrayList<ILineDataSet>()
            sets.add(setInt1)
            sets.add(setInt2)
            sets.add(setLimit)
            //set the data into the chart and refresh the chart
            val data = LineData(sets)
            setData(data)
            invalidate()

        }

    }
}