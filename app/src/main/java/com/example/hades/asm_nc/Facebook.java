package com.example.hades.asm_nc;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Facebook extends AppCompatActivity {
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://www.facebook.com/fpt.polytechnichcm/"))
                .build();
        ShareButton shareButton = (ShareButton)findViewById(R.id.sharebutton);
        shareButton.setShareContent(content);

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.hades.loginfacebook",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException e)
        {
        }
        catch (NoSuchAlgorithmException e)
        {
        }


        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                // App code
                Toast.makeText(Facebook.this, "dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                String chuoi=loginResult.getAccessToken().getUserId();
                xulysaukhilogin(loginResult);
            }
            @Override
            public void onCancel()
            {
                // App code
                Toast.makeText(Facebook.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(FacebookException exception)
            {
                // App code
                Toast.makeText(Facebook.this, "Loi", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    public void xulysaukhilogin(LoginResult loginResult){
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),new GraphRequest.GraphJSONObjectCallback()
        {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response)
            {
                Log.v("LoginActivity", response.toString());
                // Application code
                try {
                } catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(Facebook.this, "loi"+ e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name");
        request.setParameters(parameters);request.executeAsync();


    }
}
