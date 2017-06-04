package jeancarlos.firebase01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText editNome, editEmail;
    Button enviar, receber;
    TextView txt;

    DatabaseReference rootRef, demoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editNome = (EditText) findViewById(R.id.editName);
        editEmail = (EditText) findViewById(R.id.editEmail);
        enviar = (Button) findViewById(R.id.btnEnviar);
        receber = (Button) findViewById(R.id.btnReceber);
        txt = (TextView) findViewById(R.id.textView3);

        // Referencia do firebase
        rootRef = FirebaseDatabase.getInstance().getReference();

        demoRef = rootRef.child("users");

        enviar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //String value = edit.getText().toString();
                //demoRef.push().setValue(value);
                //demoRef.child("user").push().setValue(user);
                //demoRef.child("username").setValue(editNome.getText().toString());
                //demoRef.child("email").setValue(editEmail.getText().toString());


                User user = new User();

                user.username = editNome.getText().toString();
                user.email = editEmail.getText().toString();


                demoRef.push().setValue(user);


            }
        });

        receber.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                demoRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        User user =  new User();

                        String value = dataSnapshot.getValue(user.username.);
                        txt.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        txt.setText("Não deu");
                    }
                });
            }
        });
    }

}
