package com.thanmanhvinh.movieandnews.data

import com.thanmanhvinh.movieandnews.data.local.PreferencesHelper
import com.thanmanhvinh.movieandnews.data.remote.ApiHelper

interface DataManager : ApiHelper, PreferencesHelper{
}