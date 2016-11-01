package com.example.deniswilson.itabus.Listar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deniswilson.itabus.Administrador.Municipal;
import com.example.deniswilson.itabus.R;


/**
 * Created by Denis Wilson on 31/10/2016.
 */

public class ListaCustomizada extends ArrayAdapter<Municipal> {

    private int resource = 0; // Id para preencher.
    private LayoutInflater inflater; // Objeto que irá armazenar


    /**
     * reource: referencia do objeto do código arrayadapter (Crtl em cima da classe e dá um clique)
     * por isso, criei um construtor parecido
     */


    public ListaCustomizada (Context context, int resource){
        super(context,resource); //Chamdno o contrutor da classe pai.
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource; /*Passando o parametro*/
    }


    /**
     *Método getView, responsável pela vizualição para os dados do ListView veja o ArrayAdapter
     *
     */

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder viewHolder = null;

        if (convertView == null){
            viewHolder = new ViewHolder(); /*Criando a instancia da classe*/

            view = inflater.inflate(resource, parent, false);/*Criando a interface*/

            /*Chamando cada objeto do layout*/
            viewHolder.ImgOnibus = (ImageView) view.findViewById(R.id.imgOnibus);
            viewHolder.txvBairro = (TextView) view.findViewById(R.id.txvBairro);
            viewHolder.txvCodigoOnibus = (TextView)view.findViewById(R.id.txvCodigoOnibus);

            view.setTag(viewHolder);/*Associando ao objeto view. Se chamar de novo, já vai estar armazenado os objetos na memória*/
            convertView = view;
        }else{
            viewHolder = (ViewHolder)convertView.getTag(); /*guardando os objeto para utilizar posteriormente*/
            view = convertView;
        }

        Municipal municipal = getItem(position);
        /*
        * Recuperando os objetos, */
        viewHolder.ImgOnibus.setImageResource(municipal.getImgOnibus(position));
        viewHolder.txvBairro.setText(municipal.getBairro());
        viewHolder.txvCodigoOnibus.setText(municipal.getCodigo());
        return view;

    }

    /*
    * Criando uma classe, para armazenar os objetos para não ficar chamando
    * o método findView toda hora, e ficar gastando memória.
    * */
    static class ViewHolder{
        ImageView ImgOnibus;
        TextView txvBairro;
        TextView txvCodigoOnibus;
    }

}
