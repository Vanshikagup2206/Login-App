package com.vanshika.loginapp

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vanshika.loginapp.databinding.FragmentOtpBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OtpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OtpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var binding : FragmentOtpBinding ?= null
    private var email = ""
    private var num1 = ""
    private var num2 = ""
    private var num3 = ""
    private var num4 = ""
    private var otp = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            email = it.getString("email")?:""
            otp = it.getInt("otp").toString()
            num1 = it.getInt("num1").toString()
            num2 = it.getInt("num2").toString()
            num3 = it.getInt("num3").toString()
            num4 = it.getInt("num4").toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentOtpBinding.inflate(layoutInflater)
        return binding?.root
//        return inflater.inflate(R.layout.fragment_otp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.etGivenEmail?.setText(email)
        binding?.et1?.doOnTextChanged { _, _, _, _ ->
            val otp = binding?.et1?.text?.toString()?:""
            if(otp.length==1){
                binding?.et2?.requestFocus()
            }
        }
        binding?.et2?.doOnTextChanged { _, _, _, _ ->
            val otp = binding?.et2?.text?.toString()?:""
            if(otp.length==1){
                binding?.et3?.requestFocus()
            }
        }
        binding?.et3?.doOnTextChanged { _, _, _, _ ->
            val otp = binding?.et3?.text?.toString()?:""
            if(otp.length==1){
                binding?.et4?.requestFocus()
            }
        }
        binding?.et2?.addTextChangedListener {
            val otp = binding?.et2?.text?.toString()?:""
            if(otp.isEmpty()){
                binding?.et1?.requestFocus()
            }
        }
        binding?.et3?.addTextChangedListener {
            val otp = binding?.et3?.text?.toString()?:""
            if(otp.isEmpty()){
                binding?.et2?.requestFocus()
            }
        }
        binding?.et4?.addTextChangedListener {
            val otp = binding?.et4?.text?.toString()?:""
            if(otp.isEmpty()){
                binding?.et3?.requestFocus()
            }
        }
        binding?.btnVerify?.setOnClickListener {
            if (binding?.et1?.text?.toString() == num1 && binding?.et2?.text?.toString() == num2 && binding?.et3?.text?.toString() == num3 && binding?.et4?.text?.toString() == num4) {
                Dialog(requireContext()).apply {
                    setContentView(R.layout.customdialog)
                    show()
                }
                findNavController().navigate(R.id.action_otpFragment_to_passwordFragment)
            }
            else{
                Dialog(requireContext()).apply {
                    setContentView(R.layout.customdialog2)
                    show()
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OtpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                OtpFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}