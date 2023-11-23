package sheridan.dheripu.prog20082_assignment3.item.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import sheridan.dheripu.prog20082_assignment3.R
import sheridan.dheripu.prog20082_assignment3.data.local.Priority
import sheridan.dheripu.prog20082_assignment3.ui.model.ItemFormModel
import java.util.Currency
import java.util.Locale
@Composable
fun ItemForm(
    itemFormModel: ItemFormModel,
    modifier: Modifier = Modifier,
    onNameChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    onPriorityChange: (Priority) -> Unit,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = itemFormModel.name,
            onValueChange = onNameChange,
            label = { Text(stringResource(R.string.item_name_req)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = itemFormModel.price,
            onValueChange = onPriceChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = { Text(stringResource(R.string.item_price_req)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            leadingIcon = { Text(Currency.getInstance(Locale.getDefault()).symbol) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled) {
            Text(
                text = stringResource(R.string.required_fields),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.item_priority))
            Spacer(modifier = Modifier.width(8.dp))

            PriorityRadioButton(
                priority = itemFormModel.priority,
                onPrioritySelected = { onPriorityChange(it) }
            )
        }


    }
}

@Composable
private fun PriorityRadioButton(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = priority == Priority.HIGH,
            onClick = { onPrioritySelected(Priority.HIGH) }
        )
        Text(text = Priority.HIGH.name, modifier = Modifier.padding(start = 4.dp))

        Spacer(modifier = Modifier.width(8.dp))

        RadioButton(
            selected = priority == Priority.MEDIUM,
            onClick = { onPrioritySelected(Priority.MEDIUM) }
        )
        Text(text = Priority.MEDIUM.name, modifier = Modifier.padding(start = 4.dp))

        Spacer(modifier = Modifier.width(8.dp))

        RadioButton(
            selected = priority == Priority.LOW,
            onClick = { onPrioritySelected(Priority.LOW) }
        )
        Text(text = Priority.LOW.name, modifier = Modifier.padding(start = 4.dp))
    }
}
