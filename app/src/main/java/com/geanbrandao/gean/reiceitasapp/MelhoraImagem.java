package com.geanbrandao.gean.reiceitasapp;

public class MelhoraImagem {



    public static String alteraUrl(String url){
        String[] textoSeparado = url.split("=");
        StringBuilder builder = new StringBuilder();
        builder.append(textoSeparado[0]);
        builder.append("=s900-c");
        return builder.toString();
    }
}
