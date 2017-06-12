package ezracks.com.ezracks.ezracks;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.crashlytics.android.Crashlytics;
import ezracks.com.ezracks.R;
import ezracks.com.ezracks.utils.Pref;
import io.fabric.sdk.android.Fabric;


public class MainActivity extends BaseActivity {

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    /** Called when the activity is first created. */
    Thread splashTread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.activity_main);
/**
 * animation for splash Screen
 */
        StartAnimations();
    }
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(1000);
                        waited += 1000;
                    }
                    /**
                     * auto login is on then go to the new order screen else go to the login screen.
                     */
                    Log.e("alreadylogin",""+Pref.getValue(MainActivity.this,"isAlreadyLogin",""));
                    Log.e("isRememberMe",""+Pref.getValue(MainActivity.this,"isRememberMe",""));


                    if(Pref.getValue(MainActivity.this,"isAlreadyLogin","").equalsIgnoreCase("1"))
                    {
                        Intent intent = new Intent(MainActivity.this,
                                NewOrderActivity.class);
                        startActivity(intent);
                        finish();

                    }else
                    {
                        Intent intent = new Intent(MainActivity.this,
                                LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        finish();

                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                }

            }
        };
        splashTread.start();

    }

}

