package es.uem.sancho.david.actividadlayouts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListActivity extends android.app.ListActivity {

    public static enum Layouts {
        FRAME("Frame Layout", FrameLayout.class),
        RELATIVE("Relative Layout", RelativeLayout.class),
        LINEAR_VERTICAL("LinearVertical Layout", LinearVerticalLayout.class),
        SCROLL_VIEW("ScrollView Layout", ScrollViewLayout.class),
        TABLE("Table Layout", TableLayout.class),
        GRID("Grid Layout", GridLayout.class);

        private String title;
        private Class<?> dstActivity;

        private Layouts(String title, Class<?> dstActivity) {
            this.title = title;
            this.dstActivity = dstActivity;
        }

        public String getTitle() {
            return title;
        }

        public Class<?> getDestinationActivity() {
            return dstActivity;
        }

        public static String[] getList() {
            Layouts[] layouts = values();
            String[] lista = new String[layouts.length];
            for (int i = 0; i < lista.length; i++) {
                lista[i] = layouts[i].getTitle();
            }
            return lista;
        }

        public static Layouts getLayout(int pos) {
            Layouts[] layouts = values();
            return layouts.length > pos ? layouts[pos] : null;
        }

        public static Class<?> getDestinationActivity(int pos) {
            Layouts layout = getLayout(pos);
            return layout == null ? null : layout.getDestinationActivity();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        Layouts.getList());

        setListAdapter(adapter);

        final Context context = this;

        ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class<?> claseActividad = Layouts.getDestinationActivity(position);
                if (claseActividad != null) {
                    Intent intent = new Intent(context, claseActividad);
                    startActivity(intent);
                }
            }
        });
    }
}
