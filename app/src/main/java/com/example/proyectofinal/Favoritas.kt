import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.proyectofinal.ItemGuitar
import com.example.proyectofinal.getGuitars

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FavGuitarGridView(innerPadding: PaddingValues) {
    val context = LocalContext.current
    LazyVerticalGrid(
        modifier = Modifier
            .consumeWindowInsets(innerPadding)
            .padding(8.dp),
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = innerPadding,
        content = {
            items(getGuitars()) {
                ItemGuitar(it) {
                    Toast.makeText(context, it.modelo, Toast.LENGTH_SHORT).show()
                }
            }
        })
}

