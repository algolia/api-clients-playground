class SearchController < ApplicationController
  def index
    @contacts = Contact.search("John")
    render :json => @contacts
  end
end
