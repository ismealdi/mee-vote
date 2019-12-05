package com.ismealdi.vote.ui.place

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.ismealdi.meepopup.base.AmActivity
import com.ismealdi.meepopup.schema.Place
import com.ismealdi.meepopup.schema.Review
import com.ismealdi.meepopup.schema.User
import com.ismealdi.meepopup.util.common.Constants
import com.ismealdi.vote.R
import com.ismealdi.vote.adapter.ChooserAdapter
import com.ismealdi.vote.adapter.ReviewAdapter
import com.ismealdi.vote.databinding.ViewPlaceBinding
import kotlinx.android.synthetic.main.view_place.*

class PlaceActivity : AmActivity<ViewPlaceBinding>(R.layout.view_place) {

    private var place: Place? = null
    private var chooserAdapter: ChooserAdapter? = null
    private var reviewAdapter: ReviewAdapter? = null

    override fun initView(savedInstanceState: Bundle?) {
        pageBottomLine(false)
        pageBack(true)
        pageTitle(getString(R.string.title_place), false)

        handleIntent()
        initLists()
    }

    override fun handleIntent() {
        super.handleIntent()

        intent?.let { data ->
            data.getSerializableExtra(Constants.INTENT.DATA.place)?.let { placeData ->
                place = placeData as Place
                binding.place = place
            }
        }
    }

    override fun listener() {
        super.listener()

        nestedScrolling.setOnScrollChangeListener { _: NestedScrollView?, _: Int, scrollY: Int, _: Int, _: Int ->

            place?.let {
                val location = IntArray(2)
                labelPlaceAddress.getLocationOnScreen(location)

                if(scrollY >= location[1] - 50) {
                    pageTitle(it.nameCapitalize())
                    pageBottomLine(true)
                }else{
                    pageTitle(getString(R.string.empty))
                    pageBottomLine(false)
                }
            }
        }
    }

    private fun initLists() {
        initListChooser()
        initListReview()
    }

    private fun initListChooser() {
        chooserAdapter = ChooserAdapter(this)

        listChooser.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        listChooser.adapter = chooserAdapter

        val users = listOf(
            User(name = "Aldi Maulana"),
            User(name = "Astri Yulianingsih"),
            User(name = "Mukti Abdul Azis"),
            User(name = "Zaelani Ibrahim"),
            User(name = "Aahil Khalis Maulana"),
            User(name = "Astri Yulianingsih"),
            User(name = "Mukti Abdul Azis"),
            User(name = "Zaelani Ibrahim"),
            User(name = "Aahil Khalis Maulana"),
            User(name = "Astri Yulianingsih"),
            User(name = "Mukti Abdul Azis"),
            User(name = "Zaelani Ibrahim"),
            User(name = "Aahil Khalis Maulana"),
            User(name = "Astri Yulianingsih"),
            User(name = "Mukti Abdul Azis"),
            User(name = "Zaelani Ibrahim"),
            User(name = "Aahil Khalis Maulana"),
            User(name = "Astri Yulianingsih"),
            User(name = "Mukti Abdul Azis"),
            User(name = "Zaelani Ibrahim"),
            User(name = "Aahil Khalis Maulana")
        )

        chooserAdapter?.submitList(users)
    }

    private fun initListReview() {
        val currentUid = "010025"

        reviewAdapter = ReviewAdapter(this, currentUid)

        listReviews.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        listReviews.adapter = reviewAdapter

        val reviews = listOf(
            Review(uid = "010021", review = "Tempatnya dijamin rame kalau di al ghazaly, abang mee popup paling dinanti!"),
            Review(uid = "010022", review = "Tempatnya dijamin rame kalau di al ghazaly, abang mee popup paling dinanti!"),
            Review(uid = "010023", review = "Tempatnya dijamin rame kalau di al ghazaly, abang mee popup paling dinanti!"),
            Review(uid = "010024", review = "Tempatnya dijamin rame kalau di al ghazaly, abang mee popup paling dinanti!"),
            Review(uid = "010025", review = "Tempatnya dijamin rame kalau di al ghazaly, abang mee popup paling dinanti!"),
            Review(uid = "010026", review = "Tempatnya dijamin rame kalau di al ghazaly, abang mee popup paling dinanti!"),
            Review(uid = "010027", review = "Tempatnya dijamin rame kalau di al ghazaly, abang mee popup paling dinanti!"),
            Review(uid = "010028", review = "Tempatnya dijamin rame kalau di al ghazaly, abang mee popup paling dinanti!")
        )

        reviewAdapter?.submitList(reviews)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.place, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuReport -> {

            }
        }

        return super.onOptionsItemSelected(item)
    }

}