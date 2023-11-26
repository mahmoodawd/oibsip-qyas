package dev.awd.qyas.screens.calculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.awd.qyas.models.UnitCategory
import dev.awd.qyas.ui.theme.QyasTheme
import dev.awd.qyas.utils.viewModelFactory

@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier,
    category: UnitCategory,
    onBackPressed: () -> Unit,
) {
    val viewModel =
        viewModel<CalculatorViewModel>(key = category.title, factory = viewModelFactory {
            CalculatorViewModel(category)
        })


    var isSourceValueEmpty by remember {
        mutableStateOf(true)
    }
    val sourceUnitLetter = viewModel.sourceUnit.collectAsStateWithLifecycle().value.letter
    val targetUnitLetter = viewModel.targetUnit.collectAsStateWithLifecycle().value.letter

    val result by viewModel.calculationResult.collectAsStateWithLifecycle()
    val resultText = (if (isSourceValueEmpty) "To" else result.toString()).plus(" ")
        .plus(targetUnitLetter)

    val unitList by viewModel.unitList.collectAsStateWithLifecycle()
    val categoryTitle by viewModel.categoryTitle.collectAsStateWithLifecycle()
    val categoryIcon by viewModel.categoryIcon.collectAsStateWithLifecycle()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(
                Alignment.Start
            )
        ) {
            Image(
                painter = painterResource(id = categoryIcon),
                contentDescription = categoryTitle
            )
            Text(
                text = categoryTitle,
                style = MaterialTheme.typography.titleLarge,
            )
        }
        UnitInputField(
            hint = "From $sourceUnitLetter",
            unitsList = unitList,
            onValueChanged = {
                isSourceValueEmpty = it.isBlank()
                viewModel.onSourceValueChanged(it)
            },
            onUnitChanged = { viewModel.onSourceUnitChanged(it) }
        )

        UnitOutputField(
            text = resultText,
            unitsList = unitList,
            onUnitChanged = { viewModel.onTargetUnitChanged(it) }
        )

        BackButton(onClick = onBackPressed)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitInputField(
    modifier: Modifier = Modifier,
    hint: String,
    unitsList: List<String>,
    readOnly: Boolean = false,
    onValueChanged: (String) -> Unit,
    onUnitChanged: (String) -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = text,
            placeholder = { Text(text = hint) },
            onValueChange = {
                text = it
                onValueChanged(it)
            },
            readOnly = readOnly,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colorScheme.primary,
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = modifier
                .border(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .padding(vertical = 4.dp)
        )
        UnitSelectionButton(
            unitsList,
            onUnitChanged = onUnitChanged,
            initialSelectedUnit = unitsList.first()
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitOutputField(
    modifier: Modifier = Modifier,
    text: String,
    unitsList: List<String>,
    onUnitChanged: (String) -> Unit
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text,
            maxLines = 1,
            textAlign = TextAlign.Left,
            modifier = modifier
                .padding(vertical = 4.dp)
                .defaultMinSize(
                    minWidth = TextFieldDefaults.MinWidth,
                    minHeight = TextFieldDefaults.MinHeight
                )
                .border(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .padding(8.dp)
        )
        UnitSelectionButton(
            unitsList,
            onUnitChanged = onUnitChanged,
            initialSelectedUnit = unitsList.last()
        )

    }
}

@Composable
private fun UnitSelectionButton(
    unitsList: List<String>,
    initialSelectedUnit: String,
    onUnitChanged: (String) -> Unit
) {
    var isMenuExpanded by remember {
        mutableStateOf(false)
    }
    var selectedUnit by remember { mutableStateOf(initialSelectedUnit) }

    Button(shape = RoundedCornerShape(14.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(8.dp),
        onClick = { isMenuExpanded = true }) {
        Text(text = selectedUnit)
        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
    }
    if (isMenuExpanded) {
        UnitSelectionDialog(
            unitsList = unitsList,
            selectedUnit = selectedUnit,
            onUnitSelected = {
                selectedUnit = it
                onUnitChanged(it)
            },
            dismissDialog = {
                isMenuExpanded = false
            })
    }
}

@Composable
fun UnitSelectionDialog(
    unitsList: List<String>,
    selectedUnit: String,
    onUnitSelected: (String) -> Unit,
    dismissDialog: () -> Unit
) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.background,
        titleContentColor = MaterialTheme.colorScheme.primary,
        onDismissRequest = dismissDialog,
        title = {
            Text(text = "Select Unit")
        },
        confirmButton = {},
        text = {
            LazyColumn {
                items(unitsList) { unit ->
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(8.dp)
                            .clickable {
                                onUnitSelected(unit)
                                dismissDialog()
                            },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = selectedUnit == unit,
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colorScheme.secondary,
                                unselectedColor = Color.Transparent
                            ),
                            modifier = Modifier.background(
                                color = MaterialTheme.colorScheme.onSurface,
                                shape = CircleShape
                            )
                        )
                        Text(text = unit, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }
        }
    )
}


@Composable
private fun BackButton(onClick: () -> Unit) {
    Button(
        shape = RoundedCornerShape(14.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        contentPadding = PaddingValues(all = 12.dp),
        onClick = onClick
    ) {
        Text(text = "Back", fontSize = 20.sp, modifier = Modifier.padding(horizontal = 4.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorPreview() {
    QyasTheme {
        CalculatorScreen(
            category = UnitCategory.Storage(),
            onBackPressed = {},
        )
    }
}