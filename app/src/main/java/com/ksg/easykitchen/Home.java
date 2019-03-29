package com.ksg.easykitchen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private Button btnAddProducts,btnDisplayAll,btnDisplayAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.btnAddProducts :
                i =new Intent(this,AddProducts.class);
                startActivity(i);
                break;


                default:
                    break;
    }

  //  @Override
//    public void onClick(View vw) {
//        Intent i;
//
//        switch (vw.getId()){
//            case R.id.levelOne :
//                i =new Intent(this,LevelOne.class);
//                i.putExtra("isTimerOn",isTimerOn);//passing boolean value through intent to respective level class(timer)
//                startActivity(i);
//                break;
//            case R.id.levelTwo :
//                i =new Intent(this,LevelTwo.class);
//                i.putExtra("isTimerOn",isTimerOn);
//                startActivity(i);
//                break;
//            case R.id.levelThree :
//                i =new Intent(this,LevelThree.class);
//                i.putExtra("isTimerOn",isTimerOn);
//                startActivity(i);
//                break;
//            case R.id.levelFour :
//                i =new Intent(this,LevelFour.class);
//                i.putExtra("isTimerOn",isTimerOn);
//                startActivity(i);
//                break;
//            case R.id.aboutScreen:
//                i =new Intent(this,AboutScreen.class);
//                startActivity(i);
//                break;
//
//            default:break;
//
//        }
//
   }

}
