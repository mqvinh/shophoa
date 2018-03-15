/**
 * @license Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
		config.language = 'zh-cn';
	    config.baseFloatZIndex = 19900;
	    config.filebrowserBrowseUrl = '/shophoa/ckfinder/ckfinder.html';
	    config.filebrowserImageBrowseUrl = '/shophoa/ckfinder/ckfinder.html?type=Images';
	    config.filebrowserFlashBrowseUrl = '/shophoa/ckfinder/ckfinder.html?type=Flash';
	    config.filebrowserUploadUrl = '/shophoa/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
	    config.filebrowserImageUploadUrl = '/shophoa/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
	    config.filebrowserFlashUploadUrl = '/shophoa/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
};
