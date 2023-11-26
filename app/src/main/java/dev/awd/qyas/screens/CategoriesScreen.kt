package dev.awd.qyas.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.awd.qyas.R
import dev.awd.qyas.models.UnitCategory
import dev.awd.qyas.ui.theme.QyasTheme
import dev.awd.qyas.utils.AREA
import dev.awd.qyas.utils.LENGTH
import dev.awd.qyas.utils.PRESSURE
import dev.awd.qyas.utils.SPEED
import dev.awd.qyas.utils.STORAGE
import dev.awd.qyas.utils.TEMPERATURE
import dev.awd.qyas.utils.TIME
import dev.awd.qyas.utils.VOLUME
import dev.awd.qyas.utils.WEIGHT
import dev.awd.qyas.utils.getCategory

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    onCategoryClick: (UnitCategory) -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Select a unit",
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3)
        ) {
            items(items = catList) {
                CategoryItem(title = it.first, icon = it.second) {
                    val category = getCategory(it.first)
                    onCategoryClick(category)
                }
            }
        }
    }
}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = title,
            modifier
                .background(
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = RoundedCornerShape(12.dp)
                )
                .shadow(
                    elevation = 4.dp,
                    spotColor = Color(0x303A2759),
                    ambientColor = Color(0x303A2759)
                )
                .clickable { onClick() }
        )
        Text(
            text = title.lowercase(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(4.dp)
        )
    }

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
private fun CategoriesPreview() {
    QyasTheme {
        CategoriesScreen {}
    }
}

val catList = listOf(
    WEIGHT to R.drawable.icon_weight,
    VOLUME to R.drawable.icon_volume,
    TEMPERATURE to R.drawable.icon_temperature,
    LENGTH to R.drawable.icon_length,
    SPEED to R.drawable.icon_speed,
    AREA to R.drawable.icon_area,
    TIME to R.drawable.icon_time,
    PRESSURE to R.drawable.icon_pressure,
    STORAGE to R.drawable.icon_storage,
)