import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bmi.R
import com.example.bmi.Utility.getApplication
import java.text.SimpleDateFormat
import java.util.*

class BMIViewModel : ViewModel() {
    var bmiValue = MutableLiveData<Float>()
    var bmiColor = MutableLiveData<Int>()
    var metricUnits = MutableLiveData<Boolean>()
    var history = MutableLiveData<List<String>>()



    init {
        bmiValue.value = 0.0f
        bmiColor.value = R.color.black
        metricUnits.value = true
        history.value = emptyList()

        Log.d("BMIViewModel", "BMIViewModel created")
        Log.d("BMIViewModel", "bmiValue: ${bmiValue.value}")
        Log.d("BMIViewModel", "bmiColor: ${bmiColor.value}")
        Log.d("BMIViewModel", "metricUnits: ${metricUnits.value}")
        Log.d("BMIViewModel", "history: ${history.value}")
    }



    fun updateColor(bmi: Float)
    {
        val colorResId = when {
            bmi < 18.5 -> R.color.underweight
            bmi < 25 -> R.color.healthy
            bmi < 30 -> R.color.overweight
            bmi < 40 -> R.color.obese
            else -> R.color.severe_obesity
        }
        bmiColor.value = colorResId

        Log.d("BMIViewModel", "bmiColor: ${bmiColor.value}")
    }

    fun calculateBMI(weight: Float, height: Float) {
        val bmi = weight / (height * height)
        bmiValue.value = bmi

        updateColor(bmi)

        updateHistory(bmi)
    }

    fun updateHistory(bmi: Float)
    {
        val historyList = mutableListOf<String>()

        historyList.addAll(history.value?.map { entry ->
            entry
        } ?: emptyList())

        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        historyList.add("$bmi;$timestamp")

        while (historyList.size > 10) {
            historyList.removeAt(0)
        }



        history.value = historyList

        Log.d("BMIViewModel", "history: ${history.value}")
    }

    fun updateHistory(List: List<String>)
    {
        history.value = List
    }

    fun changeUnits()
    {
        metricUnits.value = !metricUnits.value!!
    }


}

