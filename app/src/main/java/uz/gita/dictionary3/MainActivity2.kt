package uz.gita.dictionary3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import uz.gita.dictionary2.mvvm.DictionaryViewModel
import uz.gita.dictionary2.mvvm.DictionaryViewModelImpl
import uz.gita.dictionary3.adapters.DictionaryAdapter
import uz.gita.dictionary3.databinding.ActivityMain2Binding
import uz.gita.dictionary3.mvvm.Repository2
import uz.gita.dictionary3.mvvm.Repository2Impl

class MainActivity2 : AppCompatActivity(),TextToSpeech.OnInitListener  {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var tts:TextToSpeech
    private val adapter: DictionaryAdapter by lazy { DictionaryAdapter() }
    private val viewModel: Repository2 by viewModels<Repository2Impl>()
    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.adapter = adapter

        viewModel.cursorLiveData.observe(this){
            if (it.count == 0){
                binding.emptyPlaceholder.visibility = View.VISIBLE
            }else{
                binding.emptyPlaceholder.visibility = View.GONE
            }
            adapter.submitCursor(it)
        }

        binding.favourites.setOnClickListener {
            viewModel.openMain()
        }

        adapter.setSoundClickListener { dictionary ->
            speackOut(dictionary.english)
        }
        tts = TextToSpeech(this,this)

        viewModel.openMainLiveData.observe(this){
            finish()
        }
        viewModel.openSecondLiveData.observe(this){
            var intent = Intent(this@MainActivity2,SecondActivity::class.java)
            intent.putExtra("id",it.id)
            startActivity(intent)
        }

        adapter.setItemClickListener {
            viewModel.changeFavourite(it)
        }


        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                handler.postDelayed({
                    viewModel.filter(newText)
                },300)
                return true
            }

        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.filter("1")
    }

    override fun onInit(p0: Int) {
        if (p0 == TextToSpeech.SUCCESS){
            val result = tts!!
        }
    }

    private fun speackOut(text:String){
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH,null,"")
    }
}