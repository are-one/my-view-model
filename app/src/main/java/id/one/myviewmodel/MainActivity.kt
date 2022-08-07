package id.one.myviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.one.myviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    // private lateinit var viewModel: MainViewModel

    // Cara 2 - inisialisasi viewModel dengan memanfaatkan delegation "by viewModels()"
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cara 1 - Inisialisasi viewModel => Menghubungkan View Model dengan Activity
        // viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        displayResult()

        binding.btnCalculate.setOnClickListener {
            val width = binding.edtWidth.text.toString()
            val height = binding.edtHeight.text.toString()
            val length = binding.edtLength.text.toString()

            when {
                width.isEmpty() -> {
                    binding.edtWidth.error = "Masih kosong"
                }
                height.isEmpty() -> {
                    binding.edtHeight.error = "Masih kosong"
                }
                length.isEmpty() -> {
                    binding.edtLength.error = "Masih kosong"
                }
                else -> {
                    viewModel.calculate(width, height, length)
                    displayResult()
                }
            }
        }
    }

    private fun displayResult(){
        binding.tvResult.text = viewModel.result.toString()
    }
}