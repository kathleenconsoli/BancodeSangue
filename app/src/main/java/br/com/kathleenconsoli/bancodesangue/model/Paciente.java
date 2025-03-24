package br.com.kathleenconsoli.bancodesangue.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

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
           Log.e("Paciente", e.getMessage());
        }
    }
    //CONSTRUTOR - Inicializa os atributos para gerar Objeto Json
    public Paciente () {
        this.setNomePaciente(" ");
        this.setDatadenascimento(" ");
        this.setSexo("");
        this.setJadoouantes("");
        this.setUltimadoacao("");
        this.setTiposanguineo(" ");
    }
    //Metodo retorna o objeto com dados no formato JSON
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("id", this.nomePaciente);
            json.put("nome", this.datadenascimento);
            json.put("senha", this.sexo);
            json.put("email", this.jadoouantes);
            json.put("aceite", this.ultimadoacao);
            json.put("nascimento", this.tiposanguineo);
        } catch (JSONException e) {
            Log.e("Paciente", e.getMessage());
        }
        return json;
    }

    //métodos
    public String getNomePaciente(){
        return this.nomePaciente;
    }
    public void setNomePaciente(String np){
        if(np.length() > 4 && !np.contains("1,2,3,4,5,6,7,8,9,0,@")) {
            this.nomePaciente = np;
        }else {
            this.nomePaciente = "nome_paciente";
        }
    }

    public String getDatadenascimento() {
        return this.datadenascimento;
    }

    public void setDatadenascimento(String datadenascimento) {
        this.datadenascimento = datadenascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getJadoouantes() {
        return jadoouantes;
    }

    public void setJadoouantes(String jadoouantes) {
        this.jadoouantes = jadoouantes;
    }

    public String getUltimadoacao() {
        return ultimadoacao;
    }

    public void setUltimadoacao(String ultimadoacao) {
        this.ultimadoacao = ultimadoacao;
    }

    public String getTiposanguineo() {
        return tiposanguineo;
    }

    public void setTiposanguineo(String tiposanguineo) {
        this.tiposanguineo = tiposanguineo;
    }
}
