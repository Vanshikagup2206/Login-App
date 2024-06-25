package com.vanshika.loginapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vanshika.loginapp.databinding.FragmentLoginBinding
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var binding : FragmentLoginBinding ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        return binding?.root
//        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnGetOtp?.setOnClickListener {
            if(binding?.etEnterYourEmail?.text?.toString().isNullOrEmpty()){
                binding?.etEnterYourEmail?.error = resources.getString(R.string.enter_your_email)
            }else if(!Patterns.EMAIL_ADDRESS.matcher(binding?.etEnterYourEmail?.getText().toString()).matches()){
                binding?.etEnterYourEmail?.error = resources.getString(R.string.enter_a_valid_email)
            }
            else {
                val num1 = Random.nextInt(0,9)
                val num2 = Random.nextInt(0,9)
                val num3 = Random.nextInt(0,9)
                val num4 = Random.nextInt(0,9)
                val bundle = Bundle()
                bundle.putString("email", binding?.etEnterYourEmail?.text?.toString())
                bundle.putString("otp","$num1$num2$num3$num4")
                findNavController().navigate(R.id.action_loginFragment_to_otpFragment, bundle)
                try{
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.setType("text/email")
                    intent.putExtra(Intent.EXTRA_TEXT,"Your generated OTP is : $num1$num2$num3$num4")
                    startActivity(intent)
                } catch(exception : Exception){
                    Toast.makeText(requireContext(), "sorry cannot open email", Toast.LENGTH_SHORT).show()}
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
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}