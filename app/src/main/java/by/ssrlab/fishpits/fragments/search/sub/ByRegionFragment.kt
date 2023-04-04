package by.ssrlab.fishpits.fragments.search.sub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.ssrlab.fishpits.R
import by.ssrlab.fishpits.databinding.FragmentByRegionBinding
import by.ssrlab.fishpits.utils.vm.main.MainActivityVM
import by.ssrlab.fishpits.utils.vm.ui.sub.bychosen.ChosenUIVM
import by.ssrlab.fishpits.utils.vm.ui.sub.tables.regriv.RegRivUIVM
import by.ssrlab.fishpits.utils.vm.ui.sub.tables.regriv.sub.ByRegUIVM

class ByRegionFragment: Fragment() {

    private lateinit var binding: FragmentByRegionBinding
    private val uiVM: ByRegUIVM by activityViewModels()
    private val regRivUIVM: RegRivUIVM by activityViewModels()
    private val activityVM: MainActivityVM by activityViewModels()
    private val chosenUIVM: ChosenUIVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentByRegionBinding.inflate(layoutInflater)

        activityVM.setToolbarTitle("Regions")

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        activityVM.setToolbarTitle("Regions")
        uiVM.setNavController(regRivUIVM.getNavController())
        binding.textView.setOnClickListener {
            chosenUIVM.chosenOne = 0
            uiVM.navigate(R.id.action_regRivHolderFragment_to_chosenFragment)
        }
    }
}