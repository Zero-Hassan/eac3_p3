package hassanzerouali.ioc.cat.eac3_p3_zerouali_hassan;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable titleAnim;

    ImageView donuts;
    ImageView ull;
    ImageView red_gear;
    ImageView green_gear;
    ImageView blue_gear;

    boolean gears_are_visible;

    static final int CLOCKWISE=1;
    static  final int ANTICLOCKWISE=-1;

    MediaPlayer mp ;
    boolean simpson_is_screaming=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      // Instanciar la imagen del titlo

        ImageView titleImage = (ImageView) findViewById(R.id.title_image);

       // añadir la lista de animation al background del titlo

       titleImage.setBackgroundResource(R.drawable.animated_title);

       // establicir la animatoin al backround del tito

        titleAnim = (AnimationDrawable) titleImage.getBackground();

        // Iniciar la animation

        titleAnim.start();

        this.init_gears();
        MediaPlayer mp = MediaPlayer.create(this, R.raw.the_simpsons);


    }
    /**
     * Metodo para instanciar las imagens a mostrar/oclutar
     *  cuando se hace click sobre el titlo
     * */

    public void init_gears(){
        donuts = (ImageView) findViewById(R.id.donut_image);
        donuts.setVisibility(View.INVISIBLE);

         ull = (ImageView) findViewById(R.id.ull_image);
         ull.setVisibility(View.INVISIBLE);

        red_gear = (ImageView) findViewById(R.id.eng_vermell_image);
        red_gear.setVisibility(View.INVISIBLE);

        green_gear = (ImageView) findViewById(R.id.eng_verd_image);
        green_gear.setVisibility(View.INVISIBLE);

         blue_gear = (ImageView) findViewById(R.id.eng_blau_image);
         blue_gear.setVisibility(View.INVISIBLE);

         gears_are_visible = false;

    }
    /**
     * Metodo para ocultar y mostrar las imagens animadas cuando
     * se hace click sobre el titulo
     *
     * */
    public void trigger_gearas(View v){

        if(gears_are_visible){
            ull.setVisibility(View.INVISIBLE);
            donuts.setVisibility(View.INVISIBLE);
            red_gear.setVisibility(View.INVISIBLE);
            green_gear.setVisibility(View.INVISIBLE);
            blue_gear.setVisibility(View.INVISIBLE);
        }else{
            ull.setVisibility(View.VISIBLE);
            donuts.setVisibility(View.VISIBLE);
            red_gear.setVisibility(View.VISIBLE);
            green_gear.setVisibility(View.VISIBLE);
            blue_gear.setVisibility(View.VISIBLE);
            this.start_gears_animation();
            this.animate_donut();
            this.animate_ull();
        }

        gears_are_visible=!gears_are_visible;
    }
    /**
     *
     * inicia la animacion de un engranage
     *
     * @param gear el engranage a animar
     *
     * @param direction el sentido de la animacion
     *                  -1 contra sentido del reloj
     *                  1 en sentido del reloj
     *
     * */

    public void set_gear_animation(View gear, int direction){
        ObjectAnimator animator = ObjectAnimator.ofFloat(gear,"rotation",0,direction * 360);
        animator.setDuration(1000);
        animator.setRepeatCount(Animation.INFINITE);
        animator.start();
    }

    public void start_gears_animation(){
        set_gear_animation(red_gear,ANTICLOCKWISE);
        set_gear_animation(green_gear,CLOCKWISE);
        set_gear_animation(blue_gear,ANTICLOCKWISE);
    }

    /**
     *  anima la propiedad translateY del donuts desde 50dp hacia arriba
     *
     *  hasta 250p hacia abajo
     *
     * */

    public void animate_donut(){

        ObjectAnimator animator = ObjectAnimator.ofFloat(donuts,"translationY",-50, 250);
        animator.setDuration(1000);
        animator.setRepeatCount(Animation.INFINITE);
        animator.start();
    }

    public void animate_ull(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(ull,"rotation",50,-50);
        animator.setDuration(1000);
        animator.setRepeatCount(Animation.INFINITE);
        animator.start();
    }

    public void play_sound(View v){
        if(mp!=null){
            if(simpson_is_screaming){
                mp.stop();
            }else{
                mp.start();
            }
            simpson_is_screaming=!simpson_is_screaming;
        }

    }
}
