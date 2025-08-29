package io.carlease.sales.adapter.in.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import io.carlease.sales.domain.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route("fillout")
public class FillOutContractView extends VerticalLayout {
    @Autowired
    private SalesVaadinController salesVaadinController;

    public FillOutContractView() {
        TextField numberField = new TextField("Contract number: ");
        TextField customerField = new TextField("Customer: ");
        TextField carField = new TextField("Car: ");
        NumberField amountField = new NumberField("Price: ");
        ComboBox<Currency> currencyComboBox = new ComboBox<>("Currency: ");
        currencyComboBox.setItems(Currency.values());
        Button saveButton = new Button("Save");

        saveButton.addClickListener(clickEvent -> {
            try {
                salesVaadinController.saveContract(
                        numberField.getValue(),
                        customerField.getValue(),
                        carField.getValue(),
                        amountField.getValue(),
                        currencyComboBox.getValue().toString());
                Notification.show("Contract saved", 2000, Notification.Position.MIDDLE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setAlignItems(FlexComponent.Alignment.CENTER);
        add(numberField, customerField, carField, amountField, currencyComboBox, saveButton);
    }

}
