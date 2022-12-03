package com.example.materialtest

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val fruits = mutableListOf(
        ChaoZhou("牌坊街", R.drawable.pfj,"    牌坊、骑楼、古桥、古井、美食，它们构成了潮州的牌坊街，但牌坊街上的精彩却不止这些：茗香四溢的工夫茶、看着来往人群回顾过去的老者、街角处晒太阳的小猫、与朋友相聚品尝甜品……\n"+"    在你闲暇之时，不妨到潮州走走，在牌坊街上探寻潮州古城的前世今生。"),
        ChaoZhou("湘子桥", R.drawable.xzq,"    它是中国第一也是世界第一座启闭式桥梁，以其“十八梭船二十四洲”的独特风格与河北赵州桥、 泉州洛阳桥、北京卢沟桥（亦作芦沟桥）并称 中国四大古桥。是潮汕地区著名文物旅游胜地，也是全国重点文物保护单位   。"),
        ChaoZhou("广济楼", R.drawable.lou,"    广济门城楼是一座宫殿式三层歇山顶阁楼，外城门原有“东为万春”门额。楼置于高大厚实的台基上，拱门中开，面阔七间，进深五间，前后面为木石柱相衔接支撑，并跨出城墙外，成为骑楼。屋面铺双层大青瓦，各层出檐均嵌蓝琉璃勾头滴水，雕栏画栋，四面环窗，飞阁流丹，巍峨壮观。\n"),
        ChaoZhou("凤凰塔", R.drawable.fht,"    韩江边上有一座非常有“特点”的古塔，印象十分深刻：潮州自从有了凤凰洲开始，韩江三分成了东、西、北三溪，其中以北溪面积最小，旱时溪水常涸，因此叫涸溪。涸溪与韩江分流处，有一座建于明代的古塔，叫涸溪塔。又因为涸溪塔与凤凰洲和凤凰台相对，又名叫凤凰塔。"),
        ChaoZhou("西马路美食街", R.drawable.xmlmsj,"    这条老街，也保留着最古早的潮州味道，虽然它们只是潮州美食的一小部分。\n" +
                "    在这里，能找到的潮州小食有：\n" +
                "    春饼、麦粿、猪肠胀糯米、咸水粿、朥饺、炸饺、卷煎、粽球、豆干肉丸、炸红薯、鼠壳粿、南瓜粿、红桃粿、芋粿、芋丸、土豆粿、笋粿、韭菜粿、芝麻粿、紫薯角、莲蓉酥、玉米酥、香橙酥、糯米卷、蛋黄卷、马蹄卷、椰子球、红腰豆角、甘草水果、鱼饺、鱼丸、鱼卷、鱼面……")
//        Fruit("Grape", R.drawable.grape,""),
//        Fruit("Pineapple", R.drawable.pineapple,""),
//        Fruit("Strawberry", R.drawable.strawberry,""),
//        Fruit("Cherry", R.drawable.cherry,""),
//        Fruit("Mango", R.drawable.mango,"")
    )

    val fruitList = ArrayList<ChaoZhou>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        navView.setCheckedItem(R.id.navCall)
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }
        fab.setOnClickListener { view ->
            view.showSnackbar("Data deleted", "Undo") {
                "Data restored".showToast(this)
            }
        }
        initFruits()
        val layoutManager = GridLayoutManager(this, 1)//1:一行一个；2：一行两个
        recyclerView.layoutManager = layoutManager
        val adapter = CZAdapter(this, fruitList)
        recyclerView.adapter = adapter
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        swipeRefresh.setOnRefreshListener {
            refreshFruits(adapter)
        }
    }

    private fun refreshFruits(adapter: CZAdapter) {
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                initFruits()
                adapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun initFruits() {
        fruitList.clear()
        //一个页面放10行
//        repeat(10) {
//            val index = (0 until fruits.size).random()
//            fruitList.add(fruits[index])
//        }
        for (i in 0 until fruits.size) {
            fruitList.add(fruits[i])
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
            R.id.backup -> Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show()
        }
        return true
    }

}
