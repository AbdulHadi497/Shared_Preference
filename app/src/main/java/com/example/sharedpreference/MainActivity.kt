package com.example.sharedpreference

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.View
import com.example.sharedpreference.Preference.customPreference
import com.example.sharedpreference.Preference.defaultPreference
import com.example.sharedpreference.Preference.password
import com.example.sharedpreference.Preference.userId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var customer_Login = "User Login"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Save.setOnClickListener(this)
        Clear.setOnClickListener(this)
        Show.setOnClickListener(this)
        Default.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var pref = customPreference(this,customer_Login)
        when(v?.id){
            R.id.Save -> {
                pref.password = (Edit_txt_2.text.toString())
                pref.userId = (Edit_txt_1.text.toString())
            }
//            R.id.Clear -> {
//                pref.clearValues
//            }
            R.id.Show -> {
                Edit_txt_1.setText(pref.userId.toString())
                Edit_txt_2.setText(pref.password)
            }
            R.id.Default -> {
                val defaultPref = defaultPreference(this)
                Edit_txt_1.setText(defaultPref.userId.toString())
                Edit_txt_2.setText(defaultPref.password)
            }
        }
    }

}

object Preference{
    var user_Id = "User_Id"
    var user_Password = "User_Password"

    fun defaultPreference(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun customPreference(context: Context, name: String): SharedPreferences = context.getSharedPreferences(
        name,Context.MODE_PRIVATE)

    fun SharedPreferences.editMe(operation:(SharedPreferences.Editor) -> Unit) {val edit_Me = edit()
    operation(edit_Me)
    edit_Me.apply()
    }
    var SharedPreferences.userId
    get() = getString("User_Id","ABC")
    set(value) {
        editMe {
            it.putString(user_Id, value)
        }
    }
    var SharedPreferences.password
    get() = getString("user_Password","123")
    set(value) {
        editMe {
            it.putString(user_Password, value)
        }
    }
//    var SharedPreferences.clearValues
//        get() = { }
//        set(value) {
//            editMe {
//                it.clear()
//                userId = ("")
//                user_Password = ("")
//            }
//        }
    fun SharedPreferences.Editor.put(pair: Pair<String, Any>){
        val key = pair.first
        val value = pair.second
        when(value){
            is String -> putString(key, value)
//            is Int -> putInt(key, value)
//            is Boolean -> putBoolean(key, value)
//            is Long -> putLong(key, value)
//            is Float -> putFloat(key, value)
            else -> error("Only Relevent Will Work")
        }
        }
    }
