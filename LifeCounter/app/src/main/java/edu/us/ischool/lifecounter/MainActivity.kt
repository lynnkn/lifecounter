package edu.us.ischool.lifecounter

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RowHolder(row: View) : RecyclerView.ViewHolder(row) {
    var playerLabel: TextView? = null
    var lifeLabel: TextView? = null

    var pOne: Button? = null
    var mOne: Button? = null
    var pFive: Button? = null
    var mFive: Button? = null

    var template: String? = null

    init {
        playerLabel = row.findViewById<Button>(R.id.playerLabel)
        lifeLabel = row.findViewById<Button>(R.id.lifeLabel)

        pOne = row.findViewById<Button>(R.id.pOne)
        mOne = row.findViewById<Button>(R.id.mOne)
        pFive = row.findViewById<Button>(R.id.pFive)
        mFive = row.findViewById<Button>(R.id.mFive)

        template = "Life Total: %d"
    }

    // binding our player labels
    fun bindModel(item: String) {
        // playerLabel is nullable so have to put !!
        playerLabel!!.text = item
        lifeLabel!!.text = String.format(template!!, 20)
    }
}

class MainActivity : AppCompatActivity() {
    // field variables
    private lateinit var playerList: RecyclerView

    class IconicAdapter(val activity: Activity) : RecyclerView.Adapter<RowHolder>() {
        private val playerLabels = arrayOf("Player 1", "Player 2", "Player 3", "Player 4")

        override fun getItemCount() : Int { return playerLabels.size }

        // recycler view inside activity
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RowHolder {
            return RowHolder(
                // displays the row
                activity.layoutInflater.inflate(R.layout.row, parent, false)
            )
        }

        override fun onBindViewHolder(holder: RowHolder, position: Int) {
            // asks row holder to bind this data into its UI
            holder.bindModel(playerLabels[position])
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // bind adapter to the RecyclerView
        playerList = findViewById<RecyclerView>(R.id.playerList)
        val adapter = IconicAdapter(this)
        playerList.adapter = adapter
        playerList.layoutManager = LinearLayoutManager(this);
    }


}
