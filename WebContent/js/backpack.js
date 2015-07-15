/**
 * 
 */
var childList;
$(document).ready(
		function() {
			childList = backpack.createChildList("childList", "childListTable",
					"childDetailDiv");
			childList.initialize();
			// bindButtonEvents();
		});

/**
 * usage - var childList1 = backpack.createChildList("childList1");
 * childList1.initialize();
 */
var backpack = (function(BACKPACK) {
	BACKPACK.childList = {
		name : "",
		childListTableId : "",
		childDetailDivId : "",
		initialize : function() {
			this.addHeader(this.childListTableId);
			this.refreshChildListTable(this.childListTableId);
		},
		addHeader : function(tableId) {
			$("#" + tableId)
					.html(
							"<thead><tr><th>ID</th><th>First Name</th><th>Last Name</th></tr></thead><tbody></tbody>");
		},
		refreshChildListTable : function(tableId) {
			var that = this;
			$("#" + tableId + " tbody tr").remove();
			backpack.childDataAccess.getChildListAsync().done(function(list) {
				$.each(list, function(key, val) {
					that.addChildRow(tableId, val);
				});
			});
		},
		addChildRow : function(tableId, child) {
			$("#" + tableId + " tbody")
					.append(
							"<tr><td>"
									+ child.childId
									+ "</td><td>"
									+ child.firstName
									+ "</td><td>"
									+ child.lastName
									+ "</td><td><input type=\"button\" value=\"Select\" onclick=\""
									+ this.name + ".getChild(" + child.childId
									+ ")\" /></td></tr>");

		},
		getChild : function(childId) {
			var that = this;
			backpack.childDataAccess.getChildAsync(childId).done(
					function(data) {
						that.setChildDetails(childId, data.firstName,
								data.lastName, data.backpack, data.healthCheck, data.haircut);
					});
		},
		setChildDetails : function(childId, firstName, lastName, backpack, healthCheck, haircut) {
			$("#childId").val(childId);
			$("#firstName").val(firstName);
			$("#lastName").val(lastName);
			if (backpack > 0) {
				$('#backpackCheckbox').prop('checked', true);
			} else {
				$('#backpackCheckbox').prop('checked', false);
			}
			if (healthCheck > 0) {
				$('#healthCheckCheckbox').prop('checked', true);
			} else {
				$('#healthCheckCheckbox').prop('checked', false);
			}
			if (haircut > 0) {
				$('#haircutCheckbox').prop('checked', true);
			} else {
				$('#haircutCheckbox').prop('checked', false);
			}		}
	}
	BACKPACK.createChildList = function(name, childListTableId,
			childDetailDivId) {
		var newChildList = Object.create(BACKPACK.childList);
		newChildList.name = name;
		newChildList.childListTableId = childListTableId;
		newChildList.childDetailDivId = childDetailDivId;
		return newChildList;
	}
	return BACKPACK;
}(backpack || {}));

backpack.baseUrl = $(location).attr('protocol') + '//'
		+ $(location).attr('host');
backpack.childDataAccess = childDataAccess;

function clearDetails() {
	$("#childId").val("");
	$("#firstName").val("");
	$("#lastName").val("");
	$('#backpackCheckbox').prop('checked', false);
	$('#healthCheckCheckbox').prop('checked', false);
	$('#haircutCheckbox').prop('checked', false);
}

function lookupChild() {
	var childId = $("#childId").val();
	clearDetails();
	childList.getChild(childId);
}

function saveChild() {
	var child = {};
	child.childId = $("#childId").val();
	child.firstName = $("#firstName").val();
	child.lastName = $("#lastName").val();
	child.backpack = $("#backpackCheckbox").prop("checked") ? 1 : 0;
	child.healthCheck = $("#healthCheckCheckbox").prop("checked") ? 1 : 0;
	child.haircut = $("#haircutCheckbox").prop("checked") ? 1 : 0;
	clearDetails();
	backpack.childDataAccess
			.getChildAsync(child.childId)
			.done(
					function(existingChild) {
						if (typeof (existingChild) !== "undefined"
								&& existingChild.childId > 0) {
							backpack.childDataAccess
									.updateChildAsync(child)
									.done(
											function(updatedChild) {
												if (typeof (updatedChild) !== "undefined"
														&& updatedChild.childId > 0) {
													childList
															.getChild(updatedChild.childId);
													childList
															.refreshChildListTable(childList.childListTableId);
												}
											});
						}
					})
			.fail(
					function() {
						backpack.childDataAccess
								.insertChildAsync(child)
								.done(
										function(insertedChild) {
											if (typeof (insertedChild) !== "undefined"
													&& insertedChild.childId > 0) {
												childList
														.getChild(insertedChild.childId);
												childList
														.refreshChildListTable(childList.childListTableId);
											}
										});
					});
}