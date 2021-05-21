package com.renatsayf.mycheatsheets.ui.screen2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.renatsayf.mycheatsheets.R
import com.renatsayf.mycheatsheets.databinding.Scree2FragmentBinding
import java.util.*

private const val recordAudioRequestCode: Int = 3698

class Scree2Fragment : Fragment(R.layout.scree2_fragment)
{
    private lateinit var binding: Scree2FragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        return inflater.inflate(R.layout.scree2_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        binding = Scree2FragmentBinding.bind(view)

        binding.micButton.setOnClickListener {
            // TODO Runtime permission Step 4
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
            {
                recordAudioPermission.launch(Manifest.permission.RECORD_AUDIO)
                return@setOnClickListener
            }
            runSpeechRecognizer()
        }

        binding.resultTV.onItemClickListener = object : AdapterView.OnItemClickListener
        {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long)
            {
                val text = (p1 as TextView).text
                return
            }

        }
    }

    private fun runSpeechRecognizer()
    {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            action = RecognizerIntent.ACTION_RECOGNIZE_SPEECH
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Говорите в микрофон...")
        }
        speechRecognizer.launch(intent)
    }

    private val speechRecognizer = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        val data = result.data
        data?.let {
            val listExtra = it.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (!listExtra.isNullOrEmpty())
            {
                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, listExtra)
                binding.resultTV.apply {
                    setAdapter(adapter)
                    showDropDown()
                }
            }
        }
        return@registerForActivityResult
    }


    // TODO Runtime permission Step 3
    private val recordAudioPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) Toast.makeText(requireContext(), "Отлично. Теперь можно распознавать речь...", Toast.LENGTH_LONG).show()
        return@registerForActivityResult
    }

}