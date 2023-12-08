import javax.swing.table.AbstractTableModel;


class MonModel extends AbstractTableModel{

    private Object[][] data;
    private String[] title;

    //Constructeur

    public MonModel(Object[][] data, String[] title) {
    this.data = data; 
    this.title = title;

    }

    //Retourne le titre de la colonne à l'indice spécifié
    public String getColumnName(int col) {

    return this.title[col];
    }

    //Retourne le nombre de colonnes

    public int getColumnCount() {
    return this.title.length;
    }

    //Retourne le nombre de lignes
    public int getRowCount() {
    return this.data.length;
     }

    //Retourne la valeur à l'emplacement spécifié
    public Object getValueAt (int row, int col) {
    return this.data[row][col];

    }


     public void setValueAt(Object value, int row, int col) {
         if (!getColumnName(col).equals("Age")) {
    	 // On interdit la modification sur certaines colonnes 
         data[row][col] = value;
     }
 }

public boolean isCellEditable(int row, int col) {
    return !getColumnName(col).equals("Age");
}

}