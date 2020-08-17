Rails.application.routes.draw do
  get 'search/index'

  root 'search#index'
end
