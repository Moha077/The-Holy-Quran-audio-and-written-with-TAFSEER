package com.example.quran.Fragment

import MyCustomDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.quran.Activities.MainActivity
import com.example.quran.Activities.SignInActivity

import com.example.quran.ApiControllers.Request.AyaService
import com.example.quran.ApiControllers.Request.TafsirService
import com.example.quran.ApiControllers.Response.AyaIndexResponse
import com.example.quran.ApiControllers.Response.AyaResponse
import com.example.quran.ApiControllers.Response.TafsirResponse
import com.example.quran.DataBase.ServiceRoom
import com.example.quran.Models.Mofasir
import com.example.quran.Models.Surah
import com.example.quran.Models.Verset
import com.example.quran.R
import com.example.quran.moushaff.Moushaf
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activitysignin.*
import kotlinx.android.synthetic.main.fragment_detail_surah.*
import kotlinx.android.synthetic.main.fragment_detail_verset.*
import kotlinx.android.synthetic.main.fragment_detail_verset.audioBtn
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class DetailSurah(val surah: Surah) : Fragment(R.layout.fragment_detail_surah)
    {
        var played = false
        var position2=0
    var preapred = false
        val listmokrii_api = arrayListOf<Mofasir>(
            Mofasir(0, "sa3ood_al-shuraym/"),
            Mofasir(1, "salah_alhashim/"),
            Mofasir(2, "sudais_shuraim_and_english/"),
            Mofasir(3, "muhammad_abdulkareem/"),
            Mofasir(4, "husary_muallim_kids_repeat/")
        )
        val listmokrii = arrayListOf<Mofasir>(
            Mofasir(0, "سعود الشريم"),
            Mofasir(1, " صالح الهاشم"),
            Mofasir(2, "سودايس شريم والإنجليزية"),
            Mofasir(3, "محمد عبد الكريم"),
            Mofasir(4, "حصري معلم اولاد")
        )
        val mofasirin = arrayListOf<Mofasir>(Mofasir(1, "التفسير الميسر"),
            Mofasir(2, "تفسير الجلالين"),
            Mofasir(7, "تفسير القرطبي"),
            Mofasir(8, "تفسير الطبري")

        )
    private val mediaPlayer =  MediaPlayer();
    var postion=0

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val mushafBtn = view.findViewById<MaterialButton>(R.id.button)
        val ayatsBtn = view.findViewById<MaterialButton>(R.id.button_ayats)
        val addFavoris = view.findViewById<TextView>(R.id.favori)
        val audiolayout = view.findViewById<LinearLayout>(R.id.audioLayout)
        val sourattext= view.findViewById<TextView>(R.id.textsourat)
        val nbrMots = view.findViewById<TextView>(R.id.nbrMots)
        nbrMots.text = surah.NbrAyat.toString()

        sourattext.text ="  تفاصيل السورة "+surah.NomSourat
            mushafBtn.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    Toast.makeText(requireContext()," يرجى الانتظار...", Toast.LENGTH_SHORT).show()

                    val intent= Intent(view.context, Moushaf::class.java)
                    intent.putExtra("id_surat",surah.IdSourat.toInt())
                    startActivity(intent)
                }
            }
            )

        val versets = ServiceRoom.database.versetDao().getVersetsBySurah(surah.IdSourat)
        /*addFavoris.setOnClickListener {
            MyCustomDialog(verset).show(requireActivity()!!.supportFragmentManager, "MyCustomFragment")
        }*/

        var i=0
            ayatsBtn.setOnClickListener {
                val Ayats_Fragment=Ayats_Fragment(surah,mofasirin.get(position2).id)
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment,  Ayats_Fragment)
                    addToBackStack(null)
                    commit()
                }
            }

    val detail_textView = view.findViewById<TextView>(R.id.ayaAr)
    detail_textView.text = versets.get(0).Text_AR



        val mokriList = view.findViewById<Spinner>(R.id.ListMokrii)
        val arrayAdaptermokrii = ArrayAdapter(requireContext(), R.layout.dropdown_item,listmokrii)
        mokriList.adapter = arrayAdaptermokrii
            mokriList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


                    preapred  =false
                    if (played) {
                        mediaPlayer.reset();
                        // mediaPlayer.release();
                        audioBtn.setImageResource(R.drawable.ic_play)
                        played = false
                    }
                    postion=position


                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

        audiolayout.setOnClickListener {
            if (checkForInternet(requireContext())) {
                if(!preapred){
                var sura=""
                if (surah.IdSourat<10){
                    sura="00"+surah.IdSourat.toString()

                }else if (surah.IdSourat<100)  {   sura="0"+surah.IdSourat.toString()

                }else   {   sura=surah.IdSourat.toString() }
                val uri =  "https://download.quranicaudio.com/quran/"+listmokrii_api.get(postion).name+sura+".mp3"
                prepareAudio(uri)
            preapred = true }
            if (played) {
                mediaPlayer.pause()
                audioBtn.setImageResource(R.drawable.ic_play)
                played = false

            }else{
                Toast.makeText(requireContext()," يرجى الانتظار...", Toast.LENGTH_SHORT).show()
                mediaPlayer.start()
                audioBtn.setImageResource(R.drawable.ic_pause)
                played = true

            }

            } else {
                Toast.makeText(requireContext(), "يرجى الاتصال بالانترنت", Toast.LENGTH_SHORT).show()
            }
        }



            val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item,mofasirin)

            val dropList = view.findViewById<Spinner>(R.id.Listmof)
            dropList.adapter = arrayAdapter

            dropList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                     position2  =position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }


    val ayaEng = view?.findViewById<TextView>(R.id.ayaEn)

  // val adapterView = view.findViewById<AutoCompleteTextView>(R.id.autoComplete)
    //adapterView.setAdapter(arrayAdapter)

    val url = "http://salamquran.com/en/api/v6/"

    val retrofit = Retrofit.Builder().baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val ayaApiTranslate = retrofit.create(AyaService::class.java)
    val response = ayaApiTranslate.getAya(versets.get(0).ayaIndex)
    response.enqueue(object : Callback<AyaResponse>{
        override fun onResponse(call: Call<AyaResponse>, response: Response<AyaResponse>) {
            val res = response.body()
            ayaEng.text = res?.result?.translate?.text

        }
        override fun onFailure(call: Call<AyaResponse>, t: Throwable) {
        }

    })










    }



    private fun prepareAudio(audioUrl: String) {


        // initializing media player

        mediaPlayer.reset()
        // below line is use to set the audio
        // stream type for our media player.
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        // below line is use to set our
        // url to our media player.


        try {
            mediaPlayer.setDataSource(audioUrl);
            // below line is use to prepare
            // and start our media player.
            mediaPlayer.prepare();




        } catch ( e : IOException) {
            e.printStackTrace();
        }
        // below line is use to display a toast message.

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.stop()
    }


        private fun checkForInternet(context: Context): Boolean {

            // register activity with the connectivity manager service
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            // if the android version is equal to M
            // or greater we need to use the
            // NetworkCapabilities to check what type of
            // network has the internet connection
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                // Returns a Network object corresponding to
                // the currently active default data network.
                val network = connectivityManager.activeNetwork ?: return false

                // Representation of the capabilities of an active network.
                val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

                return when {
                    // Indicates this network uses a Wi-Fi transport,
                    // or WiFi has network connectivity
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                    // Indicates this network uses a Cellular transport. or
                    // Cellular has network connectivity
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                    // else return false
                    else -> false
                }
            } else {
                // if the android version is below M
                @Suppress("DEPRECATION") val networkInfo =
                    connectivityManager.activeNetworkInfo ?: return false
                @Suppress("DEPRECATION")
                return networkInfo.isConnected
            }
        }

}





