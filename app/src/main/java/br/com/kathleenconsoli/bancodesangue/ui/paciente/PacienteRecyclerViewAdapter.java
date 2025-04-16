package br.com.kathleenconsoli.bancodesangue.ui.paciente;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.kathleenconsoli.bancodesangue.databinding.FragmentConPacienteBinding;
import br.com.kathleenconsoli.bancodesangue.model.Paciente;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Paciente}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PacienteRecyclerViewAdapter extends RecyclerView.Adapter<PacienteRecyclerViewAdapter.ViewHolder> {

    private final List<Paciente> mValues;

    public PacienteRecyclerViewAdapter(List<Paciente> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentConPacienteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getNomePaciente());
        holder.mContentView.setText(mValues.get(position).getTiposanguineo());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Paciente mItem;

        public ViewHolder(FragmentConPacienteBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}