package uz.gita.dictionary3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.viewModels
import uz.gita.dictionary3.data.entity.DictionaryEntity
import uz.gita.dictionary3.databinding.ActivitySecondBinding
import uz.gita.dictionary3.mvvm.Presenter
import uz.gita.dictionary3.mvvm.PresenterImpl
import uz.gita.dictionary3.repository.Repository
import uz.gita.dictionary3.repository.RepositoryImpl

class SecondActivity : AppCompatActivity() ,TextToSpeech.OnInitListener{

    private lateinit var tts:TextToSpeech
    private lateinit var binding: ActivitySecondBinding
    private val viewModel:Presenter by viewModels<PresenterImpl>()
    private lateinit var data:DictionaryEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle:Bundle? = intent.extras
        var id =  bundle!!.getLong("id",-1)
        viewModel.getDataById(id)

        viewModel.backLiveData.observe(this){
            finish()
        }

        tts = TextToSpeech(this,this)
        viewModel.showWordInfoLiveData.observe(this){
            data = it
            binding.english.text = it.english
            binding.transckip.text = it.transcript
            binding.type.text = it.type
            binding.countable.text = it.countable
            binding.uzbek.text = it.uzbek
            if (it.isFavourite == 0){
                binding.star.setImageResource(R.drawable.ic_baseline_star_outline_24)
            }else{
                binding.star.setImageResource(R.drawable.star_in)
            }
        }

        binding.favourites.setOnClickListener {
            viewModel.openFavourites()
        }

        binding.sound.setOnClickListener {
            speackOut(data.english)
        }

        viewModel.openFavouriteLiveData.observe(this){
            var intent = Intent(this@SecondActivity,MainActivity2::class.java)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener {
            viewModel.back()
        }

        binding.star.setOnClickListener {
            viewModel.showWordInfoLiveData.observe(this) {
                if (it.isFavourite == 0) {
                    binding.star.setImageResource(R.drawable.star_in)
                } else {
                    binding.star.setImageResource(R.drawable.ic_baseline_star_outline_24)
                }
                viewModel.changeFavourite(it)
            }
        }
    }

    private fun speackOut(text:String){
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onInit(p0: Int) {
        if (p0 == TextToSpeech.SUCCESS){
            val result = tts!!
        }
    }
}