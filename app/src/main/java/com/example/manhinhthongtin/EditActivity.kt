package com.example.manhinhthongtin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.manhinhthongtin.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    companion object {
        const val EXTRA_FULL_NAME = "EXTRA_FULL_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        val currentName = intent.getStringExtra(EXTRA_FULL_NAME) ?: ""
        binding.etFullName.setText(currentName)

        binding.btnSave.setOnClickListener {
            val updatedName = binding.etFullName.text?.toString()?.trim() ?: ""
            val resultIntent = Intent().apply {
                putExtra(EXTRA_FULL_NAME, updatedName)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
