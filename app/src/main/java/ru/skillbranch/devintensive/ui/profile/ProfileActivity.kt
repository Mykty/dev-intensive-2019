package ru.skillbranch.devintensive.ui.profile

import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_profile.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.viewmodels.ProfileViewModel

class ProfileActivity : AppCompatActivity() {
    companion object {
        const val IS_EDIT_MODE = "IS_EDIT_MODE"
    }

    lateinit var viewModel: ProfileViewModel
    var isEditMode = false
    lateinit var viewFields: Map<String, TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Log.d("M_MainActivity", "onCreate")
        initViews(savedInstanceState)
        initViewModel()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_EDIT_MODE, isEditMode);
    }

    private fun initViews(savedInstanceState: Bundle?) {

        viewFields = mapOf(
            "nickName" to tv_nick_name,
            "rank" to tv_rank,
            "firstName" to et_first_name,
            "lastName" to et_last_name,
            "about" to et_about,
            "repository" to et_repository,
            "rating" to tv_rating,
            "respect" to tv_respect
        )

        isEditMode = savedInstanceState?.getBoolean(IS_EDIT_MODE, false) ?: false
        showCurrentMode(isEditMode)
        btn_edit.setOnClickListener {
            if (isEditMode) saveProfileInto()
            isEditMode = !isEditMode
            showCurrentMode(isEditMode)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.getProfileData().observe(this, Observer { updateUi(it) })
    }

    private fun updateUi(profile: Profile) {
        profile.toMap().also {
            for ((k, v) in viewFields) {
                v.text = it[k].toString()
            }
        }
    }

    private fun showCurrentMode(editMode: Boolean) {
        val info = viewFields.filter {
            setOf("firstName", "lastName", "about", "repository").contains(it.key)
        }

        for ((_, v) in info) {
            v as EditText
            v.isFocusable = isEditMode
            v.isFocusableInTouchMode = isEditMode
            v.isEnabled = isEditMode
            v.background.alpha = if (editMode) 255 else 0
        }

        ic_eye.visibility = if (editMode) View.GONE else View.VISIBLE
        wr_about.isCounterEnabled = isEditMode

        with(btn_edit) {
            val filter: ColorFilter? = if (editMode) {
                PorterDuffColorFilter(
                    resources.getColor(R.color.color_accent, theme), PorterDuff.Mode.SRC_IN
                )
            } else {
                null
            }

            var icon = if (editMode) {
                resources.getDrawable(R.drawable.ic_save_whit)
            } else {
                resources.getDrawable(R.drawable.ic_edit_black)
            }

            background.colorFilter = filter
            setImageDrawable(icon)

        }
    }

    private fun saveProfileInto() {
        val profile = Profile(
            firstName = et_first_name.text.toString(),
            lastName = et_last_name.text.toString(),
            about = et_about.text.toString(),
            repository = et_repository.text.toString()
        )

        viewModel.saveProfileData(profile)
    }

}
