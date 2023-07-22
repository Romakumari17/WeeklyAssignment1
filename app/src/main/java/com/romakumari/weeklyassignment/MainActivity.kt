package com.romakumari.weeklyassignment

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.romakumari.weeklyassignment.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var list= arrayListOf<String>()
    lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
        binding.list.adapter=adapter
        //fab button Start
        binding.fab.setOnClickListener {
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.itemlayout)
            dialog.getWindow()?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            var etName = dialog.findViewById<EditText>(R.id.etName)
            var Add = dialog.findViewById<Button>(R.id.Add)
            Add.setOnClickListener {
                if (etName.text.toString().isNullOrEmpty()) {
                    etName.error = "Enter the Value"
                } else {
                    list.add("${etName.text.toString()}")
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialog.show()

        }
            //end of fab
            // alert start//

            binding.list.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position, id: Long ->
                var Name:EditText?=null
                Name = findViewById(R.id.etName)
                AlertDialog.Builder(this)
                    .setMessage("Do you want to update or Return")
                    .setCancelable(true)
                    .setNeutralButton("cancel") { _, _, ->
                        Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Delete") { _, _, ->
                        Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show()
                        list.removeAt(position)
                        adapter.notifyDataSetChanged()
                    }
                    .setPositiveButton("Update") { _, _, ->
                        Name?.setText(Name.toString())
                        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()

                        //custom dialog update//
                        var dialog = Dialog(this)
                        dialog.setContentView(R.layout.customlayout)
                        dialog.getWindow()?.setLayout(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        var btnCustom: Button? = null
                        var etCustom: EditText? = null
                        btnCustom = dialog.findViewById(R.id.btnCustom)
                        etCustom = dialog.findViewById(R.id.etCustom)
                        btnCustom.setOnClickListener {
                            if (etCustom.text.toString().isNullOrEmpty()) {
                                etCustom.error = "enter the name"
                            } else {
                                list.set(position, etCustom.text.toString())
                                dialog.dismiss()
                                adapter.notifyDataSetChanged()
                            }
                        }
                        dialog.show()
                    }
                    .show()

        }

    }
}

