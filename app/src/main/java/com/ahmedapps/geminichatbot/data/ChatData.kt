package com.ahmedapps.geminichatbot.data

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.ResponseStoppedException
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Ahmed Guedmioui
 */
object ChatData {

    val api_key = "AIzaSyBtPQG2TNshIZGkNe3DjTfMRdXq8gF1ju0"

    suspend fun getResponse(prompt: String): Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro", apiKey = api_key
        )

        try {
            val response = withContext(Dispatchers.IO) {
                generativeModel.generateContent(prompt)
            }

            return Chat(
                prompt = response.text ?: "error",
                bitmap = null,
                isFromUser = false
            )

        } catch (e: Exception) {
            return Chat(
                prompt = e.message ?: "error",
                bitmap = null,
                isFromUser = false
            )
        }

    }

    suspend fun getResponseWithImage(prompt: String, bitmap: Bitmap): Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro-vision", apiKey = api_key
        )

        try {

            val inputContent = content {
                image(bitmap)
                text(prompt)
            }

            val response = withContext(Dispatchers.IO) {
                generativeModel.generateContent(inputContent)
            }

            return Chat(
                prompt = response.text ?: "error",
                bitmap = null,
                isFromUser = false
            )

        } catch (e: Exception) {
            return Chat(
                prompt = e.message ?: "error",
                bitmap = null,
                isFromUser = false
            )
        }

    }

}




















