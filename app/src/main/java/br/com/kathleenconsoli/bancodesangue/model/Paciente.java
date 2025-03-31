package br.com.kathleenconsoli.bancodesangue.model;

import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Paciente {
    //atributos
    private String nomePaciente;
    private String datadenascimento;
    private String sexo;
    private String jadoouantes;
    private String ultimadoacao;
    private String tiposanguineo;

    //CONSTRUTOR - inicializa atributos de um arquivo JSon
    public Paciente (JSONObject jp) {
        try {
            this.setNomePaciente(jp.getString("nome_paciente"));
            this.setDatadenascimento(jp.getString("data_de_nascimento"));
            this.setSexo(jp.getString("sexo"));
            this.setJadoouantes(jp.getString("ja_doou_antes"));
            this.setUltimadoacao(jp.getString("ultima_doacao"));
            this.setTiposanguineo(jp.getString("tipo_sanguineo"));
            } catch (Exception e) {
           Log.e("Paciente", Objects.requireNonNull(e.getMessage()));
        }
    }
    //CONSTRUTOR - Inicializa os atributos para gerar Objeto Json
    public Paciente () {
        this.setNomePaciente("");
        this.setDatadenascimento("1970-01-01");
        this.setSexo("");
        this.setJadoouantes("");
        this.setUltimadoacao("");
        this.setTiposanguineo(" ");
    }
    //Metodo retorna o objeto com dados no formato JSON
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("nomePaciente", this.nomePaciente);
            json.put("datadenascimento", this.datadenascimento);
            json.put("sexo", this.sexo);
            json.put("jadoouantes", this.jadoouantes);
            json.put("ultimadoacao", this.ultimadoacao);
            json.put("tiposanguineo", this.tiposanguineo);
        } catch (JSONException e) {
            Log.e("Paciente", Objects.requireNonNull(e.getMessage()));
        }
        return json;
    }

    //métodos
    public String getNomePaciente(){
        return this.nomePaciente;
    }
    public void setNomePaciente(String nomePaciente){this.nomePaciente = nomePaciente;}

    public String getDatadenascimento() {
        return this.datadenascimento;
    }
    public void setDatadenascimento(String datadenascimento) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formato =
                new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date data = (Date) formato.parse(datadenascimento);
//se chegar até aqui não deu erro no parser
        this.datadenascimento = datadenascimento;
    } catch (ParseException e) {
            this.datadenascimento = "1900-01-01";
        }
    }

    public String getSexo() {return sexo;}
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getJadoouantes() {return jadoouantes;}
    public void setJadoouantes(String jadoouantes) {
        this.jadoouantes = jadoouantes;
    }

    public String getUltimadoacao() {return ultimadoacao;}
    public void setUltimadoacao(String ultimadoacao) {
        this.ultimadoacao = ultimadoacao;
    }

    public String getTiposanguineo() {return tiposanguineo;}
    public void setTiposanguineo(String tiposanguineo) {
        this.tiposanguineo = tiposanguineo;
    }
}