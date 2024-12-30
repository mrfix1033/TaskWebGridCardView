package com.example.taskwebgridcardview

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spinnerautomulticompletetextview.AppCompatActivityWithExitMenu

class MainActivity : AppCompatActivityWithExitMenu() {
    private lateinit var gridView: GridView
    private val sites = mutableListOf(
        SiteModal("Yandex", "https://ya.ru", R.drawable.icon_yandex),
        SiteModal("Urban University", "https://urban-university.ru", R.drawable.icon_urban_university),
        SiteModal("Gismeteo", "https://gismeteo.ru", R.drawable.icon_gismeteo),
        SiteModal("Rutube", "https://rutube.ru", R.drawable.icon_rutube),
        SiteModal("Госуслуги", "https://gosuslugi.ru", R.drawable.icon_gosuslugi),
        SiteModal("Android", "https://android.com", R.drawable.logo_android)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(findViewById(R.id.toolbar))

        gridView = findViewById(R.id.gridView)

        gridView.adapter = object : BaseAdapter() {
            override fun getCount(): Int {
                return sites.size
            }

            override fun getItem(p0: Int): Any? {
                return null
            }

            override fun getItemId(p0: Int): Long {
                return 0
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                var view = convertView
                if (view == null) view = LayoutInflater.from(this@MainActivity)
                    .inflate(R.layout.card_view, parent, false)!!
                sites[position].run {
                    view.run {
                        findViewById<ImageView>(R.id.imageView).setImageResource(image)
                        findViewById<TextView>(R.id.textView).text = name
                    }
                }
                return view
            }
        }
        gridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("url", sites[position].url)
                startActivity(intent)
            }
    }
}