package com.example.manhinhthongtin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.manhinhthongtin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentFullName: String = ""

    private val editInfoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val updatedName = result.data?.getStringExtra(EditActivity.EXTRA_FULL_NAME)
            if (!updatedName.isNullOrEmpty()) {
                currentFullName = updatedName
                binding.contentMain.tvFullName.text = currentFullName
            } else {
                currentFullName = getString(R.string.default_full_name)
                binding.contentMain.tvFullName.text = currentFullName
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.toolbar)

        currentFullName = getString(R.string.default_full_name)
        binding.contentMain.tvFullName.text = currentFullName

        binding.contentMain.btnEditInfo.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java).apply {
                putExtra(EditActivity.EXTRA_FULL_NAME, currentFullName)
            }
            editInfoLauncher.launch(intent)
        }
    }
}
