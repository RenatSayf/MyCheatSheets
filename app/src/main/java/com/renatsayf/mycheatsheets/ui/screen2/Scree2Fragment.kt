package com.renatsayf.mycheatsheets.ui.screen2

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.renatsayf.mycheatsheets.R
import com.renatsayf.mycheatsheets.databinding.Scree2FragmentBinding

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
                activityResultLauncher.launch(Manifest.permission.RECORD_AUDIO)
            }
        }
    }


    // TODO Runtime permission Step 3
    private val activityResultLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()){ isGranted ->
        if (isGranted) Toast.makeText(requireContext(), "Отлично. Теперь можно распознавать речь...", Toast.LENGTH_LONG).show()
        return@registerForActivityResult
    }

}