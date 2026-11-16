# TASK 2 – Hospital Outpatient Appointment Desk (Design)

## Event model
| Event source | Event object | Listener | Action |
|---|---|---|---|
| Department JComboBox | ActionEvent | ActionListener | Refresh doctor list from selected department |
| Doctor JComboBox | ActionEvent | ActionListener | Update selected doctor |
| Slot JComboBox | ActionEvent | ActionListener | Update selected slot |
| Book JButton | ActionEvent | ActionListener | Validate fields and book |
| Window close | WindowEvent | WindowListener/WindowAdapter | Confirm if booking is in progress |

## Component containment
JFrame
- JPanel (BorderLayout)
  - JPanel (GridLayout): patient ID, department, doctor, slot
  - JPanel (FlowLayout): Book and Cancel buttons
  - JLabel: status

## Handler pseudocode
```
ON department selection:
    selectedDepartment <- departmentCombo.getSelectedItem()
    doctorCombo.removeAllItems()
    FOR EACH doctor IN doctorMap[selectedDepartment]:
        doctorCombo.addItem(doctor)

ON Book button:
    IF patientId is empty OR doctor is not selected OR slot is not selected:
        show validation error
    ELSE:
        set bookingInProgress = true
        validate patient and slot in service
        save appointment
        set bookingInProgress = false
        show confirmation

ON windowClosing:
    IF bookingInProgress:
        ask user to confirm closing
    ELSE:
        dispose window
```

## Applet lifecycle comparison
The desktop window is constructed and shown; its event handlers run in response to user actions; windowClosing handles the close request and dispose releases the window. This is not the applet lifecycle (init, start, paint, stop, destroy).

## Alternative and complexity
Preloading a map of departments to doctors requires O(d + m) storage and avoids a database round trip on every department change. Fetching on each change uses database/network latency per change but can reduce cached memory and keep data fresher.
