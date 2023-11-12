import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BMIViewModel : ViewModel() {

    private val _bmiValue = MutableLiveData<Float>()
    val bmiValue: LiveData<Float> get() = _bmiValue

    private val _bmiColor = MutableLiveData<Int>()
    val bmiColor: LiveData<Int> get() = _bmiColor

    private val _history = MutableLiveData<List<Float>>()
    val history: LiveData<List<Float>> get() = _history

    fun calculateBMI(weight: Float, height: Float, context: Context) {
        val bmi = weight / (height * height)
        _bmiValue.value = bmi

        updateColor(bmi, context)

        updateHistory(bmi, context)
    }


    private fun updateColor(bmi: Float, context: Context) {

/*        val colorResId = when {
            bmi < 18.5 -> R.color.underweightColor
            bmi < 25 -> R.color.normalWeightColor
            else -> R.color.overweightColor
        }*/
/*
        _bmiColor.value = context.getColor(colorResId)*/
    }

    private fun updateHistory(bmi: Float, context: Context) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("BMI_HISTORY", Context.MODE_PRIVATE)
        val historyList = mutableListOf<Float>()

        historyList.addAll(_history.value ?: emptyList())

        historyList.add(bmi)

        if (historyList.size > 10) {
            historyList.removeAt(0)
        }

        sharedPreferences.edit().putString("history", historyList.joinToString(",")).apply()
        
        _history.value = historyList
    }
}