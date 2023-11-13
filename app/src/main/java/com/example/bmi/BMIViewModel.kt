import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bmi.R
import com.example.bmi.Utility
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

    }



    fun updateColor(bmi: Float)
    {
        val colorResId = Utility.chooseColor(bmi)
        bmiColor.value = colorResId

    }

    fun calculateBMI(weight: Float, height: Float) {
        var bmi=0f;
        if(metricUnits.value == false)
        {
            bmi = weight / (height * height) * 703
        }
        else
        {
            bmi = weight / (height * height)
        }

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
    }

    fun updateHistory(List: List<String>)
    {
        history.value = List
    }

    fun changeUnits()
    {
        metricUnits.value = !metricUnits.value!!
        Log.d("BMIViewModel", "metricUnits: ${metricUnits.value}")
    }


}

