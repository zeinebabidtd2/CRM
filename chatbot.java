package com.example.crm;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

public class chatbot extends AppCompatActivity {
    private LinearLayout chatContainer;
    private EditText etMessage;
    private NestedScrollView chatScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatbot);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        chatContainer = findViewById(R.id.chatContainer);
        etMessage = findViewById(R.id.etMessage);
        ImageButton btnSend = findViewById(R.id.btnSend);
        chatScroll = findViewById(R.id.chatScroll);

        btnSend.setOnClickListener(v -> {
            String message = etMessage.getText().toString().trim();
            if (!message.isEmpty()) {
                addMessage("Vous: " + message, true);
                etMessage.setText("");
                
                // Réponse simple du chatbot
                String response = getBotResponse(message);
                addMessage("Bot: " + response, false);
            }
        });
    }

    private void addMessage(String text, boolean isUser) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(16);
        textView.setPadding(20, 15, 20, 15);
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 10, 0, 10);
        
        if (isUser) {
            params.gravity = Gravity.END;
            textView.setBackgroundResource(R.drawable.badge_bg);
            textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        } else {
            params.gravity = Gravity.START;
            textView.setBackgroundResource(R.drawable.edittext_bg);
            textView.setTextColor(ContextCompat.getColor(this, R.color.text_dark));
        }
        
        textView.setLayoutParams(params);
        chatContainer.addView(textView);
        
        // Scroll vers le bas
        chatScroll.post(() -> chatScroll.fullScroll(NestedScrollView.FOCUS_DOWN));
    }

    private String getBotResponse(String message) {
        String msg = message.toLowerCase();
        if (msg.contains("hello") || msg.contains("hi") || msg.contains("salut")) {
            return "Bonjour ! Comment puis-je vous aider aujourd'hui ?";
        } else if (msg.contains("ca va") || msg.contains("comment tu vas")) {
            return "Je vais très bien, merci ! Et vous ?";
        } else if (msg.contains("aide") || msg.contains("help")) {
            return "Je peux vous aider à gérer vos clients ou vos ventes.";
        } else {
            return "Désolé, je ne comprends pas encore cette commande. Essayez 'Hello' !";
        }
    }
}