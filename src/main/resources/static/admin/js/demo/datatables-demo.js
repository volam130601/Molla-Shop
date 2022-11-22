// Call the dataTables jQuery plugin
$(document).ready(function () {
    $('#productTable').DataTable({
        "aoColumnDefs": [
            {"bSortable": false, "aTargets": [5, 6, 7]},
            {"bSearchable": false, "aTargets": [5, 6, 7]}
        ]
    });
});
